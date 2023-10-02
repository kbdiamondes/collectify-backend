package com.capstone.collectify.controllers;


import com.capstone.collectify.models.User;
import com.capstone.collectify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// Enables cross origin request via @CrossOrigin.
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    // Create User
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>("User created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/users" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(userService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/users/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable Long userid){
        return userService.deleteUser(userid);
    }
    // Update a post
    @RequestMapping (value = "/users/{userid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable Long userid, @RequestBody User user){
        return userService.updateUser(userid,user);
    }
}
