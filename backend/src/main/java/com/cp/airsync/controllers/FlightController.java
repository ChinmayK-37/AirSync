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

import com.cp.airsync.entity.Flight;
import com.cp.airsync.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping
    public ResponseEntity<Flight> createFlight(
            @RequestBody Flight flight) {

        Flight savedFlight = flightService.save(flight);

        return new ResponseEntity<>(
                savedFlight,
                HttpStatus.CREATED
        );
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(
            @PathVariable Long id) {

        Flight flight = flightService.findFlightById(id);

        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                flight,
                HttpStatus.OK
        );
    }

    
    @GetMapping
    public ResponseEntity<List<Flight>> findFlights() {

        List<Flight> flights = flightService.listAll();

        return new ResponseEntity<>(
                flights,
                HttpStatus.OK
        );
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestBody Flight flight) {

        Flight existingFlight = flightService.findFlightById(id);

        if (existingFlight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setAirline(flight.getAirline());
        existingFlight.setOrigin(flight.getOrigin());
        existingFlight.setDestination(flight.getDestination());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setBasePriceEconomy(flight.getBasePriceEconomy());
        existingFlight.setBasePriceBusiness(flight.getBasePriceBusiness());
        existingFlight.setStatus(flight.getStatus());

        Flight updatedFlight = flightService.save(existingFlight);

        return new ResponseEntity<>(
                updatedFlight,
                HttpStatus.OK
        );
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(
            @PathVariable Long id) {

        Flight existingFlight = flightService.findFlightById(id);

        if (existingFlight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        flightService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}