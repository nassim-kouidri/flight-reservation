package com.esiea.flightreservation.dto;

import java.util.UUID;

public record BookingRequest(
        UUID flightId,
        UUID clientId
) {
}
