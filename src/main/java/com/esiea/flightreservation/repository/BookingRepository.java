package com.esiea.flightreservation.repository;

import com.esiea.flightreservation.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b FROM Booking b WHERE b.client.id = :clientId")
    List<Booking> findByClientId(UUID clientId);

    @Modifying
    @Query("DELETE FROM Booking b WHERE b.client.id = :clientId")
    void deleteByClientId(@Param("clientId") UUID clientId);
}
