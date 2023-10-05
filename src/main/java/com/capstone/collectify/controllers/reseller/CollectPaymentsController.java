package com.capstone.collectify.controllers.reseller;


import com.capstone.collectify.services.reseller.CollectPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/collect-payments")
public class CollectPaymentsController {

    private final CollectPaymentsService paymentCollectionService;

    @Autowired
    public CollectPaymentsController(CollectPaymentsService paymentCollectionService) {
        this.paymentCollectionService = paymentCollectionService;
    }

    @PostMapping("/{resellerId}/contracts/{contractId}/collect-payment")
    public ResponseEntity<String> collectPayment(
            @PathVariable Long resellerId,
            @PathVariable Long contractId,
            @RequestParam String paymentType) {
        try {
            paymentCollectionService.collectPayments(resellerId, contractId, paymentType);
            return ResponseEntity.ok("Payment collected successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment collection failed: " + e.getMessage());
        }
    }

}
