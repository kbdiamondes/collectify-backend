package com.capstone.collectify.services.others;


import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionWithClientAndItemDTO;

import java.util.List;

public interface PaymentTransactionService {
    Iterable<PaymentTransaction> getPaymentTransaction();

    List<PaymentTransaction> getPaymentTransactionsByClientId(Long clientId);

    List<PaymentTransactionWithClientAndItemDTO> getPaymentTransactionsWithClientAndItemByResellerId(Long resellerId);

    List<PaymentTransactionWithClientAndItemDTO> getUnpaidPaymentTransactionsWithClientAndItemByResellerId(Long resellerId);

    List<PaymentTransactionWithClientAndItemDTO> getUncollectedPaymentTransactionsWithClientAndItemByResellerId(Long resellerId);

    List<PaymentTransactionWithClientAndItemDTO> getUncollectedAndUnassignedPaymentTransactionsWithClientAndItemByResellerId(Long resellerId);

}
