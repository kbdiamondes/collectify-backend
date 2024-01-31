package com.capstone.collectify.controllers.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.services.client.DuePaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/due-payments")
public class DuePaymentsController {
    @Autowired
    private DuePaymentsService duePaymentsService;


    @GetMapping("/unpaid-contracts")
    public List<Client> getClientsWithUnpaidContracts() {
        List<Client> clientsWithUnpaidContracts = duePaymentsService.getClientsWithUnpaidContracts();
        return clientsWithUnpaidContracts;
    }

    @GetMapping("/client/{clientId}/unpaid-contracts")
    public ResponseEntity<Client> getClientWithUnpaidContracts(@PathVariable Long clientId) {
        Client client = duePaymentsService.getClientWithUnpaidContracts(clientId);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client/{clientId}/paid-contracts")
    public ResponseEntity<Client> getClientWithPaidContracts(@PathVariable Long clientId) {
        Client client = duePaymentsService.getClientWithPaidContracts(clientId);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}
