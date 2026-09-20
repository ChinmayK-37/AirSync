package com.cp.airsync.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cp.airsync.enums.FlightStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "base_price_economy", precision = 12, scale = 2)
    private BigDecimal basePriceEconomy;

    @Column(name = "base_price_business", precision = 12, scale = 2)
    private BigDecimal basePriceBusiness;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @OneToMany(mappedBy = "flight")
    @JsonManagedReference
    private List<Seat> seats;
}