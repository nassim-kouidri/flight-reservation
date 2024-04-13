package com.esiea.flightreservation.dto;


import lombok.NonNull;

import java.util.UUID;

public record CreateClientRequest(
        int passportNumber,

        @NonNull
        UUID userAccountId) {
}
