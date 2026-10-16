package com.cp.airsync.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cp.airsync.entity.PriceSnapshot;

@Repository
public interface PriceSnapshotRepository extends JpaRepository<PriceSnapshot , Long>{

}
