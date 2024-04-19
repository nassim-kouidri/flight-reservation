package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.dto.BookingRequest;
import com.esiea.flightreservation.model.Booking;
import com.esiea.flightreservation.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@Validated
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable UUID id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/save")
    public Booking saveAirport(@RequestBody BookingRequest bookingRequest) {
        return bookingService.saveBooking(bookingRequest);
    }
}
