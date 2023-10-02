package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.TransactionDetails;
import org.springframework.http.ResponseEntity;

public interface TransactionDetailsService {
    void createTransactionDetails(Long clientId, TransactionDetails transactionDetails);

    Iterable<TransactionDetails> getTransactionDetails();

    Iterable<TransactionDetails>getTransactionDetailsByClientId(Long clientId);

    ResponseEntity deleteTransactionDetails(Long clientId,Long id);

    ResponseEntity updateTransactionDetails(Long clientId,Long id, TransactionDetails transactionDetails);
}
