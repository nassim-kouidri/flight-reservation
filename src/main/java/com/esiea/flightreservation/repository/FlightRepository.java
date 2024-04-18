package com.esiea.flightreservation.repository;

import com.esiea.flightreservation.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByDepartureAndDestinationAndDepartDateAndArrivalDate(String departure, String destination, Date departDate, Date arrivalDate);
}
