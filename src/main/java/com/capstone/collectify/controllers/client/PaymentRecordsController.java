package com.capstone.collectify.controllers.client;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.TransactionHistory;
import com.capstone.collectify.services.ClientService;
import com.capstone.collectify.services.client.PaymentRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment-records")
public class PaymentRecordsController {

    @Autowired
    private PaymentRecordsService paymentRecordsService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<TransactionHistory>> getPaymentRecordsForClient(@PathVariable Long clientId) {
        // Get the client by ID
        Optional<Client> clientOptional = clientService.getClientById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            List<TransactionHistory> paymentRecords = paymentRecordsService.getPaymentRecordsForClient(client);
            if (paymentRecords != null) {
                // Log payment records for debugging
                paymentRecords.forEach(paymentRecord -> {
                    System.out.println("Order ID: " + paymentRecord.getOrderId());
                    System.out.println("Amount Paid: " + paymentRecord.getAmountPaid());
                    System.out.println("Payment Date: " + paymentRecord.getPaymentDate());
                    // Add more attributes for debugging as needed
                });
                return new ResponseEntity<>(paymentRecords, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
