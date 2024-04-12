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
                orElseThrow(() -> new IllegalArgumentException("User account not found"));
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount saveUserAccount(UserAccount userAccount) {
        if (isEmailExists(userAccount.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userAccountRepository.save(userAccount);
    }



    public UserAccount updateUserAccount(UserAccount userAccount, UUID id) {
        UserAccount user = getUserAccountById(id);
        if(!user.getEmail().equals(userAccount.getEmail()) && isEmailExists(userAccount.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setEmail(userAccount.getEmail());
        user.setFirstName(userAccount.getFirstName());
        user.setLastName(userAccount.getLastName());
        user.setAddress(userAccount.getAddress());
        user.setBirthday(userAccount.getBirthday());
        user.setTel(userAccount.getTel());

        return userAccountRepository.save(user);
    }

    public void deleteUserAccount(UUID id) {
        if (!userAccountRepository.existsById(id)) {
            throw new IllegalArgumentException("User account not found");
        }
        userAccountRepository.deleteById(id);
    }

    private boolean isEmailExists(String email) {
        return userAccountRepository.existsByEmail(email);
    }
}
