package com.cp.airsync.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cp.airsync.entity.BookingSeat;

@Repository
public interface NoShowPredictionRepository extends JpaRepository<NoShowPrediction, Long>
{
    
}
