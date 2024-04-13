package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.dto.CreateClientRequest;
import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable UUID id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@RequestBody CreateClientRequest createClientRequest) {
        Client savedClient = clientService.saveClient(createClientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody CreateClientRequest createClientRequest, @PathVariable UUID id) {
        Client updateClient = clientService.updateClient(id, createClientRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateClient);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
