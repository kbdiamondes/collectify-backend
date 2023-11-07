package com.capstone.collectify.services.others;


import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;


    @Override
    public Iterable<PaymentTransaction> getPaymentTransaction() {
        return paymentTransactionRepository.findAll();
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactionsByClientId(Long clientId) {
        // Implement this method to fetch payment transactions by client ID
        return paymentTransactionRepository.findPaymentTransactionsByClientId(clientId);
    }


}

