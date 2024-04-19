package com.esiea.flightreservation.service;

import com.esiea.flightreservation.dto.BookingRequest;
import com.esiea.flightreservation.model.Booking;
import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightService flightService;
    private final ClientService clientService;


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(UUID id) {
        return bookingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Booking with id '%s' not found", id)));
    }

    @Transactional
    public Booking saveBooking(BookingRequest bookingRequest) {
        Client client = clientService.getClientById(bookingRequest.clientId());
        Flight flight = flightService.getFlightById(bookingRequest.flightId());

        Booking booking = new Booking();
        booking.setClient(client);
        booking.setFlight(flight);

        return bookingRepository.save(booking);
    }

}
