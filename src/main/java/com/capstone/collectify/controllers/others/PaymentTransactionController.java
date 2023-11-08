package com.capstone.collectify.controllers.others;

import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.services.others.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-transactions")
@CrossOrigin
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @GetMapping
    public Iterable<PaymentTransaction> getAllPaymentTransactions() {
        return paymentTransactionService.getPaymentTransaction();
    }

    @GetMapping("/client/{clientId}")
    public List<PaymentTransaction> getPaymentTransactionsByClientId(@PathVariable Long clientId) {
        return paymentTransactionService.getPaymentTransactionsByClientId(clientId);
    }
}
