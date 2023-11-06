package com.capstone.collectify.services.client;

import com.capstone.collectify.models.TransactionHistory;
import com.capstone.collectify.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> getAllTransactionsByClient(Long clientId) {
        return transactionHistoryRepository.findByClientId(clientId);
    }

    @Override
    public List<TransactionHistory> getAllTransactions() {
        return transactionHistoryRepository.findAll();
    }
}