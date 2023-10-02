package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.TransactionHistory;
import org.springframework.http.ResponseEntity;

public interface TransactionHistoryService {
    void createTransactionHistory(Long clientId,TransactionHistory transactionHistory);

    Iterable<TransactionHistory> getTransactionHistory();

    Iterable<TransactionHistory>getTransactionHistoryByClientId(Long clientId);
    ResponseEntity deleteTransactionHistory(Long clientId,Long id);

    ResponseEntity updateTransactionHistory(Long clientId,Long id, TransactionHistory transactionHistory);
}
