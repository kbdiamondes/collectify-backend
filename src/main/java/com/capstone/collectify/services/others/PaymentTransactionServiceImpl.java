package com.capstone.collectify.services.others;


import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;


    @Override
    public Iterable<PaymentTransaction> getPaymentTransaction() {
        return paymentTransactionRepository.findAll();
    }


}

