package com.cp.airsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "cancellation",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_cancellation_booking", columnNames = {"booking_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "refund_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal refundAmount;

    @Column(name = "cancelled_at", nullable = false)
    private LocalDateTime cancelledAt;
}