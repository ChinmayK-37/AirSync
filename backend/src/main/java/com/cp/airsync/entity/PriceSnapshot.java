package com.cp.airsync.entity;

import com.cp.airsync.enums.SeatClass;
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
    name = "price_snapshot",
    indexes = {
        @Index(name = "idx_price_snapshot_flight_class_time", columnList = "flight_id, class, computed_at")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_snapshot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "class", nullable = false)
    private SeatClass seatClass;

    @Column(name = "price", precision = 12, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "demand_signal_json", columnDefinition = "TEXT")
    private String demandSignalJson;

    @Column(name = "computed_at", nullable = false)
    private LocalDateTime computedAt;
}