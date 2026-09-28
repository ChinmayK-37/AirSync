package com.cp.airsync.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp.airsync.entity.SeatHold;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.Query;

public interface SeatHoldRepository extends JpaRepository<SeatHold, Long> {
    @Query("SELECT s FROM SeatHold s WHERE s.seat.id = :seatId")
    Optional<SeatHold> findBySeatId(Long seatId);

    List<SeatHold> findByExpiresAtBefore(LocalDateTime now);

    @Query("SELECT COUNT(sh) FROM SeatHold sh WHERE sh.user.userId = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("SELECT sh FROM SeatHold sh WHERE sh.user.userId = :userId")
    List<SeatHold> findByUserId(@Param("userId") Long userId);
}