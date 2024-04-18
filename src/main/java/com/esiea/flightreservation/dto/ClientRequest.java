package com.esiea.flightreservation.dto;


import lombok.NonNull;

import java.util.UUID;

public record ClientRequest(
        int passportNumber,

        @NonNull
        UUID userAccountId) {
}
