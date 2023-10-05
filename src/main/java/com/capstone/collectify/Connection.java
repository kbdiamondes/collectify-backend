package com.capstone.collectify;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Connection {

    @GetMapping("/hello")
    public ResponseEntity<Object> connectionSuccess(){
        String message = "connected successfully.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
