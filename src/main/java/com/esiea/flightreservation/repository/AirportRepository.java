package com.esiea.flightreservation.repository;

import com.esiea.flightreservation.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {
}
