package com.capstone.collectify.services.others;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {
    Map<String, Object> findEntityInfoByUsername(String username);

    Map<String, Object> authenticateUser(String username, String password);
}
