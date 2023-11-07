package com.capstone.collectify.services.others;


import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.models.PaymentTransaction;

import java.util.List;

public interface PaymentTransactionService {
    Iterable<PaymentTransaction> getPaymentTransaction();

    List<PaymentTransaction> getPaymentTransactionsByClientId(Long clientId);
}
