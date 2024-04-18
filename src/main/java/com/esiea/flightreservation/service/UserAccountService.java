package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.UserAccount;
import com.esiea.flightreservation.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount getUserAccountById(UUID id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id '%s' not found", id)));
    }

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Transactional
    public UserAccount saveUserAccount(UserAccount userAccount) {
        if (!isValidEmail(userAccount.getEmail())) {
            String errorMessage = String.format("Invalid email format: %s", userAccount.getEmail());
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (isEmailExists(userAccount.getEmail())) {
            throw new IllegalArgumentException(String.format("The email '%s' already exists", userAccount.getEmail()));
        }

        return userAccountRepository.save(userAccount);
    }


    @Transactional
    public UserAccount updateUserAccount(UserAccount userRequest, UUID id) {
        UserAccount user = getUserAccountById(id);
        if (!isValidEmail(userRequest.getEmail())) {
            String errorMessage = String.format("Invalid email format: %s", userRequest.getEmail());
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        if (!user.getEmail().equals(userRequest.getEmail()) && isEmailExists(userRequest.getEmail())) {
            throw new IllegalArgumentException(String.format("The email '%s' already exists", userRequest.getEmail()));
        }

        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setAddress(userRequest.getAddress());
        user.setBirthday(userRequest.getBirthday());
        user.setTel(userRequest.getTel());

        return userAccountRepository.save(user);
    }

    @Transactional
    public void deleteUserAccount(UUID id) {
        if (!userAccountRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("User with id '%s' not found", id));
        }
        userAccountRepository.deleteById(id);
    }

    private boolean isEmailExists(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Matcher matcher = Pattern.compile(emailRegex).matcher(email);
        return matcher.matches();
    }

}
