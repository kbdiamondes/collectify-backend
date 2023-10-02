package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class ClientController {

    @Autowired
    ClientService clientService;

    // Create User
    @RequestMapping(value = "/client",method = RequestMethod.POST)
    public ResponseEntity<Object> createClient(@RequestBody Client client){
        clientService.createClient(client);
        return new ResponseEntity<>("Client Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/client" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(clientService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/client/{clientid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable Long clientid){
        return clientService.deleteClient(clientid);
    }
    // Update a post
    @RequestMapping (value = "/client/{clientid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClient(@PathVariable Long clientid, @RequestBody Client client){
        return clientService.updateClient(clientid,client);
    }
}
