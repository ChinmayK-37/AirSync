package com.cp.airsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cp.airsync.entity.Flight;
import com.cp.airsync.repository.FlightRepository;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // CREATE / UPDATE
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    // READ - Get flight by ID
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // READ - Get all flights
    public List<Flight> listAll() {
        return flightRepository.findAll();
    }

    // DELETE
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }
}