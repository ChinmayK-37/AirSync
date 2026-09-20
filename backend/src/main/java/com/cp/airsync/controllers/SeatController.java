package com.cp.airsync.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.airsync.entity.Seat;
import com.cp.airsync.service.SeatService;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<Seat> creatSeat(@RequestBody Seat seat) {

        Seat savedSeat = seatService.save(seat);

        return new ResponseEntity<>(
                savedSeat,
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Seat> findSeatById(@PathVariable Long id) {

        Seat seat = seatService.findSeatById(id);

        return new ResponseEntity<>(
                seat,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<List<Seat>> findSeats() {

        List<Seat> seats = seatService.listAll();

        return new ResponseEntity<>(
                seats,
                HttpStatus.OK
        );
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {

        seatService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}