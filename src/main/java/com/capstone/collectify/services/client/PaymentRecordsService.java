package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.TransactionHistory;

import java.util.List;

public interface PaymentRecordsService {
    List<TransactionHistory> getPaymentRecordsForClient(Client client);
}
