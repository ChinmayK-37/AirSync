package com.cp.airsync.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.airsync.entity.SeatHold;
import com.cp.airsync.service.SeatHoldService;


@RestController 
@RequestMapping("/api")
public class SeatHoldController {

    private final SeatHoldService seatHoldService;

    public SeatHoldController(SeatHoldService seatHoldService) {
        this.seatHoldService = seatHoldService;
    }

    @PostMapping("/flights/{flightId}/seats/{seatId}/hold")
    public ResponseEntity<SeatHold> holdSeat(
            @PathVariable Long flightId,
            @PathVariable  Long seatId,
            @RequestHeader ("X-User-Id") Long userId) {

        SeatHold hold = seatHoldService.holdSeat(flightId, seatId, userId);
        
        return new ResponseEntity<>(
            hold,
            HttpStatus.CREATED
        );    
    }

    @DeleteMapping("/flights/{flightId}/seats/{seatId}/release") 
    public ResponseEntity<Void> releaseHold(
            @PathVariable Long flightId,
            @PathVariable  Long seatId,
            @RequestHeader ("X-User-Id") Long userId){

        seatHoldService.releaseHold(flightId , seatId, userId);    
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/holds/me")
    public ResponseEntity<List<SeatHold>> getMyHolds(@RequestHeader ("X-User-Id") Long userId) {
        List<SeatHold> myHolds = seatHoldService.getMyHolds(userId);

        return new ResponseEntity<>(
            myHolds,
            HttpStatus.OK
        );
    }


}
