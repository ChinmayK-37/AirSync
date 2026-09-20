package com.cp.airsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.airsync.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

}