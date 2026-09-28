package com.cp.airsync.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.cp.airsync.entity.Seat;
import com.cp.airsync.entity.SeatHold;
import com.cp.airsync.enums.FlightStatus;
import com.cp.airsync.enums.SeatStatus;
import com.cp.airsync.repository.SeatHoldRepository;
import com.cp.airsync.repository.SeatRepository;
import com.cp.airsync.repository.UserRepository;

@Service
public class SeatHoldService {

    private static final Duration HOLD_DURATION = Duration.ofMinutes(5);
    private static final int MAX_HOLDS_PER_USER = 6;

    private final SeatRepository seatRepository;
    private final SeatHoldRepository seatHoldRepository;
    private final UserRepository userRepository;
    private final StringRedisTemplate redis;

    public SeatHoldService(SeatRepository seatRepository,
                           SeatHoldRepository seatHoldRepository,
                           UserRepository userRepository,
                           StringRedisTemplate redis) {
        this.seatRepository = seatRepository;
        this.seatHoldRepository = seatHoldRepository;
        this.userRepository = userRepository;
        this.redis = redis;
    }

    private String lockKey(Long flightId, Long seatId){
        return "hold:" + flightId + ":" + seatId;
    }

    @Transactional 
    public SeatHold holdSeat(Long flightId, Long seatId , Long userId){

        if (seatHoldRepository.countByUserId(userId) >= MAX_HOLDS_PER_USER) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                    "Maximum active holds reached");
        }
        // Layer 1: Redis lock (fast path)
        String key = lockKey(flightId, seatId);

        
        Boolean acquired = redis.opsForValue()
                    .setIfAbsent(key, String.valueOf(userId) , HOLD_DURATION);
        if(!Boolean.TRUE.equals(acquired)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat is already held");
        }

        try{
            // Layer 2: Postgres row lock (source of truth)
            Seat seat = seatRepository.findByIdForUpdate(seatId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Seat not found"));

            if (!seat.getFlight().getId().equals(flightId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Seat does not belong to this flight");
            }
            if (seat.getFlight().getStatus() != FlightStatus.SCHEDULED) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Flight is not open for booking");
            }
            if (seat.getStatus() == SeatStatus.BOOKED) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat is already booked");
            }


            LocalDateTime now = LocalDateTime.now();
            SeatHold hold = null;

            if (seat.getStatus() == SeatStatus.HELD) {
                // Redis TTL expired but the cleanup job hasn't run yet: reuse the stale row
                hold = seatHoldRepository.findBySeatId(seatId).orElse(null);
                if (hold != null && hold.getExpiresAt().isAfter(now)) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Seat is already held");
                }
            }

            if(hold == null){
                hold = new SeatHold();
                hold.setSeat(seat);
            }

            hold.setUser(userRepository.getReferenceById(userId));
            hold.setCreatedAt(now);
            hold.setExpiresAt(now.plus(HOLD_DURATION));
            hold = seatHoldRepository.save(hold);

            seat.setStatus(SeatStatus.HELD);
            seatRepository.save(seat);

            return hold;

        }catch(RuntimeException e){
            redis.delete(key);   // we own the lock, so undo it on any failure
            throw e;
        }

    }

    @Transactional 
    public void releaseHold(Long flightId , Long seatId , Long userId){

        Seat seat = seatRepository.findByIdForUpdate(seatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found"));
        
        SeatHold hold = seatHoldRepository.findBySeatId(seatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No active hold"));
        
        if (!hold.getUser().getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This hold is not yours");
        }

        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);
        seatHoldRepository.delete(hold);

        redis.delete(lockKey(flightId, seatId));

    }

    @Transactional(readOnly = true)
    public List<SeatHold> getMyHolds(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        return seatHoldRepository.findByUserId(userId).stream()
                .filter(h -> h.getExpiresAt().isAfter(now))
                .toList();
    }

    @Scheduled(fixedRate = 60000)   // every 60 seconds
    @Transactional
    public void releaseExpiredHolds() {
    
        List<SeatHold> expired = seatHoldRepository.findByExpiresAtBefore(LocalDateTime.now());
    
        for (SeatHold hold : expired) {
            Long seatId = hold.getSeat().getId();
    
            Seat seat = seatRepository.findByIdForUpdate(seatId)   // lock Seat first, always
                    .orElse(null);
    
            if (seat != null && seat.getStatus() == SeatStatus.HELD) {
                seat.setStatus(SeatStatus.AVAILABLE);
                seatRepository.save(seat);
            }
    
            seatHoldRepository.delete(hold);
            redis.delete(lockKey(seat.getFlight().getId(), seatId));   // usually already gone, harmless
        }
    }


}
