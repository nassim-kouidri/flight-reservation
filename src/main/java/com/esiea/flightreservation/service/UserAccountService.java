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
            if(userAccountRepository.existsByEmail(userAccount.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            return userAccountRepository.save(userAccount);
        }

        public void updateUserAccount(UserAccount userAccount) {
            if(userAccountRepository.existsByEmail(userAccount.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            userAccountRepository.save(userAccount);
        }

        public void deleteUserAccount(UUID id) {
            if(!userAccountRepository.existsById(id)) {
                throw new IllegalArgumentException("User account not found");
            }
            userAccountRepository.deleteById(id);
        }
}
