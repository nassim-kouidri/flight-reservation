package com.esiea.flightreservation.service;

import com.esiea.flightreservation.dto.CreateClientRequest;
import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.model.UserAccount;
import com.esiea.flightreservation.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserAccountService userAccountService;

    public Client getClientById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client saveClient(CreateClientRequest createClientRequest) {
        validatePassportNumber(createClientRequest.passportNumber());

        UserAccount user = getUserAccount(createClientRequest.userAccountId());
        Client client = new Client();
        client.setPassportNumber(createClientRequest.passportNumber());
        client.setUserAccount(user);

        return clientRepository.save(client);
    }

    @Transactional
    public Client updateClient(UUID clientId, CreateClientRequest createClientRequest) {
        Client client = getClientById(clientId);
        validatePassportNumber(createClientRequest.passportNumber());

        UserAccount user = getUserAccount(createClientRequest.userAccountId());
        client.setPassportNumber(createClientRequest.passportNumber());
        client.setUserAccount(user);

        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    private void validatePassportNumber(int passportNumber) {
        if (clientRepository.existsByPassportNumber(passportNumber)) {
            throw new IllegalArgumentException("Passport number already exists: " + passportNumber);
        }
    }

    private UserAccount getUserAccount(UUID userAccountId) {
        return userAccountService.getUserAccountById(userAccountId);
    }
}


