package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import org.springframework.http.ResponseEntity;


public interface ClientService {

//    Iterable<Client> getClient_Id(Long clientId) {
//    }

    // Create Client
    void createClient(Client client);
    // Get Client
    Iterable<Client> getUsername();
    // Delete Client
    ResponseEntity deleteClient(Long id);
    // Update a Client
    ResponseEntity updateClient(Long id, Client Client);
}