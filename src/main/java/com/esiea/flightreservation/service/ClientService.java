package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        if(clientRepository.existsByPassportNumber(client.getPassportNumber())) {
            throw new IllegalArgumentException("Passport number already exists");
        }
        return clientRepository.save(client);
    }

    public void updateClient(Client client) {
        if(clientRepository.existsByPassportNumber(client.getPassportNumber())) {
            throw new IllegalArgumentException("Passport number already exists");
        }
        clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
        if(!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}

