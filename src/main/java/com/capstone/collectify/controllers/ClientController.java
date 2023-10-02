package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
    public void payDue(@PathVariable Long clientId, @PathVariable Long contractId, @RequestBody BigDecimal amount) {
        try {
            clientService.payDue(clientId, contractId, amount);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    // Add other endpoints for Client-related operations
}