package com.capstone.collectify.controllers.others;

import com.capstone.collectify.services.others.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> findEntityInfo(@RequestBody Map<String, String> usernameMap) {
        try {
            String username = usernameMap.get("username");
            Map<String, Object> result = loginService.findEntityInfoByUsername(username);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
