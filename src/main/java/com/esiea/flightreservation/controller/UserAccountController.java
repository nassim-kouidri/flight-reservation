package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.model.UserAccount;
import com.esiea.flightreservation.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/all")
    public List<UserAccount> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }

    @GetMapping("/{id}")
    public UserAccount getUserAccountById(@PathVariable UUID id) {
        return userAccountService.getUserAccountById(id);
    }

    @PostMapping("/save")
    public UserAccount saveUserAccount(@RequestBody UserAccount userAccount) {
        return userAccountService.saveUserAccount(userAccount);
    }

    @PutMapping("/update/{id}")
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable UUID id) {
       return userAccountService.updateUserAccount(userAccount, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserAccount(@PathVariable UUID id) {
        userAccountService.deleteUserAccount(id);
    }


}
