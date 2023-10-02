package com.capstone.collectify.services;

import com.capstone.collectify.models.User;
import com.capstone.collectify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    // Find user by username
    // Create user
    public void createUser(User user){
        userRepository.save(user);
    }
    // Get all user
    public Iterable<User> getUsername(){
        return userRepository.findAll();
    }

    // Delete user
    public ResponseEntity deleteUser(Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
    }

    // Update a user
    public ResponseEntity updateUser(Long id, User user){
        // Find the user to update
        User userForUpdate = userRepository.findById(id).get();
        // Updating the username and password
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setPassword(user.getPassword());
        // Saving and Updating a user
        userRepository.save(userForUpdate);
        return new ResponseEntity<>("User updated Successfully",HttpStatus.OK);
    }

    public Optional<User> findByUsername(String username){
       // id findByUsername method returns null it will throw a NullPointerException
        // Using the .ofNullable method, it will avoid NullPointerException from happening.
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
