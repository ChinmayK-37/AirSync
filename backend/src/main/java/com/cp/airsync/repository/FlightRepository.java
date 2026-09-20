package com.cp.airsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.airsync.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}