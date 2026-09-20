package com.cp.airsync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cp.airsync.entity.Airline;
import com.cp.airsync.repository.AirlineRepository;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }


    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }


    public Airline findAirlineById(Long id) {
        return airlineRepository.findById(id).orElse(null);
    }


    public List<Airline> listAll() {
        return airlineRepository.findAll();
    }

 
    public void deleteById(Long id) {
        airlineRepository.deleteById(id);
    }
}