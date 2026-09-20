package com.cp.airsync.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.airsync.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long>{

}

