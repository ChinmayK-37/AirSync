package com.cp.airsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cp.airsync.entity.Seat;
import com.cp.airsync.repository.SeatRepository;
@Service
public class SeatService {
    public SeatRepository seatRepository;

public SeatService(SeatRepository seatRepository)
{
    this.seatRepository = seatRepository;
}

public Seat save(Seat seat)
{
    return seatRepository.save(seat);
}
public Seat findSeatById(Long id) 
{
    return seatRepository.findById(id).orElse(null);
}
public List<Seat> listAll()
{
    return seatRepository.findAll();
}

public Seat update(Seat seat)
{
    return seatRepository.save(seat);
}

public void deleteById(Long id)
{
    seatRepository.deleteById(id);
}

public void deleteAll()
{
    seatRepository.deleteAll();
}
}
