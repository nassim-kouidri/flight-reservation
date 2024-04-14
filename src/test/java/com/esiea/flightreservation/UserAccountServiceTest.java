package com.esiea.flightreservation;

import com.esiea.flightreservation.repository.UserAccountRepository;
import com.esiea.flightreservation.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAccountServiceTest {

    private UserAccountService userAccountService;

    @BeforeEach
    public void setUp() {
        UserAccountRepository userAccountRepositoryMock = mock(UserAccountRepository.class);
        userAccountService = new UserAccountService(userAccountRepositoryMock);

        when(userAccountRepositoryMock.existsByEmail(anyString())).thenReturn(false);
    }

    @Test
    public void testValidEmail() {
        assertTrue(userAccountService.isValidEmail("test@example.com"));
        assertTrue(userAccountService.isValidEmail("user123@gmail.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(userAccountService.isValidEmail("invalid_email.com"));
        assertFalse(userAccountService.isValidEmail("invalid@"));
        assertFalse(userAccountService.isValidEmail("invalid.com"));
    }
}


