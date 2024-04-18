package com.esiea.flightreservation.dto;

import java.util.Date;
import java.util.UUID;

public record FlightRequest(

        String departure,

        String destination,

        Date departDate,

        Date arrivalDate,

        int placeNumber,

        UUID airportDepartId,

        UUID airportArrivalId,

        UUID planeId) {
}
