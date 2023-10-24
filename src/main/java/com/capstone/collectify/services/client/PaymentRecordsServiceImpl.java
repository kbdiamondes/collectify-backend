package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.TransactionHistory;
import com.capstone.collectify.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentRecordsServiceImpl implements PaymentRecordsService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<TransactionHistory> getPaymentRecordsForClient(Client client) {
        return transactionHistoryRepository.findPaymentRecordsWithZeroAmount(client.getClient_id());
    }

}
