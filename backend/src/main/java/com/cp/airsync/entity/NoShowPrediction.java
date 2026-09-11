package com.cp.airsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "no_show_prediction",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_no_show_booking", columnNames = {"booking_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoShowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_show_prediction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "probability", nullable = false)
    private Double probability;

    @Column(name = "computed_at", nullable = false)
    private LocalDateTime computedAt;
}