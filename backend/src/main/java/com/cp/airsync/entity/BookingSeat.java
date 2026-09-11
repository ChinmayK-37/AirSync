package com.cp.airsync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
    name = "booking_seat",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_booking_seat_seat", columnNames = {"seat_id"})
        // a seat can only ever belong to ONE booking_seat row across the system
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "passenger_first_name", nullable = false)
    private String passengerFirstName;

    @Column(name = "passenger_last_name", nullable = false)
    private String passengerLastName;

    @Column(name = "passenger_dob")
    private LocalDate passengerDob;

    @Column(name = "passenger_passport_number")
    private String passengerPassportNumber;
}