package com.capstone.collectify.services.others;


import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.models.PaymentTransaction;

public interface PaymentTransactionService {
    Iterable<PaymentTransaction> getPaymentTransaction();
}
