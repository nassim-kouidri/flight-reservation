package com.esiea.flightreservation.dto;

import java.util.Date;

public record FlightSearchRequest(
        String departure,
        String destination,
        Date departDate,
        Date arrivalDate
) {}

