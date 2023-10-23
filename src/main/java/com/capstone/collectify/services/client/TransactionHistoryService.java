package com.capstone.collectify.services.client;

import com.capstone.collectify.models.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionHistory> getAllTransactionsByClient(Long clientId);
}