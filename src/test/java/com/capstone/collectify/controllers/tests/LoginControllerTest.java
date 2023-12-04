package com.capstone.collectify.controllers.tests;


import com.capstone.collectify.controllers.others.LoginController;
import com.capstone.collectify.services.others.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginServiceImpl loginService;

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "testUser");
        credentials.put("password", "testPassword");

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("entityId", 123L);
        expectedResult.put("tableName", "Client");

        when(loginService.authenticateUser("testUser", "testPassword")).thenReturn(expectedResult);

        // Act
        ResponseEntity<Map<String, Object>> response = loginController.login(credentials);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());

        // Verify that the authenticateUser method of the loginService was called with the correct arguments
        verify(loginService, times(1)).authenticateUser("testUser", "testPassword");
    }

    @Test
    public void testUnauthorizedLogin() {
        // Arrange
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "invalidUser");
        credentials.put("password", "invalidPassword");

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("entityId", null);
        expectedResult.put("tableName", "Not Found");

        when(loginService.authenticateUser("invalidUser", "invalidPassword")).thenReturn(expectedResult);

        // Act
        ResponseEntity<Map<String, Object>> response = loginController.login(credentials);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());

        // Verify that the authenticateUser method of the loginService was called with the correct arguments
        verify(loginService, times(1)).authenticateUser("invalidUser", "invalidPassword");
    }

    // Additional tests for error scenarios can be added
}
