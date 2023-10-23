package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.ClientService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/unpaid-contracts")
    public List<Client> getClientsWithUnpaidContracts() {
        List<Client> clientsWithUnpaidContracts = clientService.getClientsWithUnpaidContracts();
        return clientsWithUnpaidContracts;
    }

    @GetMapping("/client/{clientId}/unpaid-contracts")
    public ResponseEntity<Client> getClientWithUnpaidContracts(@PathVariable Long clientId) {
        Client client = clientService.getClientWithUnpaidContracts(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}/paid-contracts")
    public ResponseEntity<Client> getClientWithPaidContracts(@PathVariable Long clientId) {
        Client client = clientService.getClientWithPaidContracts(clientId);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }





    @GetMapping
    public ResponseEntity<Object> getClient() {
        return new ResponseEntity<>(clientService.getClient(), HttpStatus.OK);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/{id}/contracts")
    public List<Contract> getClientContracts(@PathVariable Long id) {
        return clientService.getClientContracts(id);
    }

    @PostMapping("/{clientId}/contracts")
    public Contract createContractForClient(@PathVariable Long clientId, @RequestBody Contract contract) {
        return clientService.createContractForClient(clientId, contract);
    }

    @PostMapping("/{clientId}/contracts/{contractId}/pay")
    public void payDue(@PathVariable Long clientId, @PathVariable Long contractId, @RequestBody Map<String, BigDecimal> requestBody) {
        BigDecimal amount = requestBody.get("amount");

        try {
            clientService.payDue(clientId, contractId, amount);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    // Add other endpoints for Client-related operations
}
