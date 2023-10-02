package com.capstone.collectify.services;

import com.capstone.collectify.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    // Creating a user
    void createUser(User user);
    // viewing all user
    Iterable<User> getUsername();
    // Delete a user
    ResponseEntity deleteUser(Long id);
    // Update a user
    ResponseEntity updateUser(Long id, User user);

    // Optional - defines if the method may/may not return an object of the User Class
    Optional<User> findByUsername(String username);

}
