package com.esiea.flightreservation.repository;

import com.esiea.flightreservation.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaneRepository extends JpaRepository<Plane, UUID> {
}
