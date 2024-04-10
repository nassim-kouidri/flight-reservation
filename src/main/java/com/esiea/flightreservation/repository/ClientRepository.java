package com.esiea.flightreservation.repository;

import com.esiea.flightreservation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{

    boolean existsByPassportNumber(int passportNumber);

}
