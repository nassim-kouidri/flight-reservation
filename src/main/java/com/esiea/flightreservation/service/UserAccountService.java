package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.UserAccount;
import com.esiea.flightreservation.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount getUserAccountById(UUID id) {
        return userAccountRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException(String.format("User with id '%s' not found", id)));
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount saveUserAccount(UserAccount userAccount) {
        if (isEmailExists(userAccount.getEmail())) {
            throw new IllegalArgumentException(String.format("The email '%s' already exist", userAccount.getEmail()));
        }
        return userAccountRepository.save(userAccount);
    }


    public UserAccount updateUserAccount(UserAccount userRequest, UUID id) {
        UserAccount user = getUserAccountById(id);
        if (!user.getEmail().equals(userRequest.getEmail()) && isEmailExists(userRequest.getEmail())) {
            throw new IllegalArgumentException(String.format("The email '%s' already exist", userRequest.getEmail()));
        }
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setAddress(userRequest.getAddress());
        user.setBirthday(userRequest.getBirthday());
        user.setTel(userRequest.getTel());

        return userAccountRepository.save(user);
    }

    public void deleteUserAccount(UUID id) {
        if (!userAccountRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("User with id '%s' not found", id));
        }
        userAccountRepository.deleteById(id);
    }

    private boolean isEmailExists(String email) {
        return userAccountRepository.existsByEmail(email);
    }
}
