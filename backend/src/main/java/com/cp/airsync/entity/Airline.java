package com.cp.airsync.entity;

import jakarta.persistence.*;
import com.cp.airsync.enums.AirlineStatus;


@Entity
@Table(name = "airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "airline_id")
    private Long airlineId;

    @Column(name = "iata_code", unique = true, nullable = false)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AirlineStatus status;

    public Airline() {
        
    }

    public Airline(String iataCode, String name, AirlineStatus status) {
        this.iataCode = iataCode;
        this.name = name;
        this.status = status;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirlineStatus getStatus() {
        return status;
    }

    public void setStatus(AirlineStatus status) {
        this.status = status;
    }
}