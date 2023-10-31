package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
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
        List<TransactionHistory> paymentRecords = transactionHistoryRepository.findPaymentRecordsWithZeroAmount(client.getClient_id());

        for (TransactionHistory record : paymentRecords) {
            // Fetch the associated contract for each payment record
            Contract contract = record.getContract();

            if (contract != null) {
                Collector collector = contract.getCollector();
                if (collector != null) {
                    // If there's a Collector, set the collector's full name
                    record.setCollectorName(collector.getFullName());
                } else {
                    // If no Collector is assigned, set the Reseller's username
                    record.setCollectorName(contract.getReseller().getFullName());
                }
            }
        }

        return paymentRecords;
    }

}
