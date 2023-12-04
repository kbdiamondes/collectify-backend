package com.capstone.collectify.serviceimpl.tests;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import com.capstone.collectify.services.others.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CollectorRepository collectorRepository;

    @Mock
    private ResellerRepository resellerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSuccessfulAuthenticationClient() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        Client client = new Client(123L,"testUser", "Full Name", "Address", "test@example.com", passwordEncoder.encode("testPassword"), "First", "Middle", "Last");

        when(clientRepository.findByUsername(username)).thenReturn(Optional.of(client));
        when(passwordEncoder.matches(password, client.getPassword())).thenReturn(true);

        // Act
        Map<String, Object> result = loginService.authenticateUser(username, password);

        System.out.println(client);
        System.out.println(result);

        // Assert
        assertEquals(123L, result.get("entityId"));
        assertEquals("Client", result.get("tableName"));
    }

    @Test
    public void testFailedAuthentication() {
        // Arrange
        String username = "invalidUser";
        String password = "invalidPassword";
        when(clientRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(collectorRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(resellerRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        Map<String, Object> result = loginService.authenticateUser(username, password);

        // Assert
        assertNull(result.get("entityId"));
        assertEquals("Not Found", result.get("tableName"));
    }

    // Additional tests for other entities and scenarios can be added
}
