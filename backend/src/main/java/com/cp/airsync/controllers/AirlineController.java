package com.cp.airsync.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.airsync.entity.Airline;
import com.cp.airsync.service.AirlineService;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }


    @PostMapping
    public ResponseEntity<Airline> createAirline(
            @RequestBody Airline airline) {

        Airline savedAirline = airlineService.save(airline);

        return new ResponseEntity<>(
                savedAirline,
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> findAirlineById(
            @PathVariable Long id) {

        Airline airline = airlineService.findAirlineById(id);

        if (airline == null) {
            return new ResponseEntity<>("Airline does not exists",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                airline,
                HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<List<Airline>> findAirlines() {

        List<Airline> airlines = airlineService.listAll();

        return new ResponseEntity<>(
                airlines,
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airline> updateAirline(
            @PathVariable Long id,
            @RequestBody Airline airline) {

        Airline existingAirline =
                airlineService.findAirlineById(id);

        if (existingAirline == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingAirline.setIataCode(airline.getIataCode());
        existingAirline.setName(airline.getName());
        existingAirline.setStatus(airline.getStatus());

        Airline updatedAirline =
                airlineService.save(existingAirline);

        return new ResponseEntity<>(
                updatedAirline,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(
            @PathVariable Long id) {

        Airline existingAirline =
                airlineService.findAirlineById(id);

        if (existingAirline == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        airlineService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}