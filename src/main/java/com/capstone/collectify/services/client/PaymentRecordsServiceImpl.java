package com.capstone.collectify.services.client;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import com.capstone.collectify.repositories.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentRecordsServiceImpl implements PaymentRecordsService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Override
    public List<PaymentTransactionForCollectionListDTO> getClientPaidAndCollectedPaymentTransactions(Long clientId) {
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findPaidAndCollectedPaymentTransactionsByClientId(clientId);
        List<PaymentTransactionForCollectionListDTO> paymentTransactionForCollectionList = new ArrayList<>();

        for (PaymentTransaction pt : paymentTransactions) {
            Collector collector = pt.getCollector();
            Contract contract = pt.getContract();
            Client client = contract.getClient();
            Reseller reseller = contract.getReseller();

            PaymentTransactionForCollectionListDTO dto = new PaymentTransactionForCollectionListDTO();
            dto.setPayment_transactionid(pt.getPayment_transactionid());
            dto.setOrderid(pt.getOrderid());
            dto.setPaymenttransactionid(pt.getPaymenttransactionid());
            dto.setAmountdue(pt.getAmountdue());
            dto.setStartingdate(pt.getStartingdate());
            dto.setEnddate(pt.getEnddate());
            dto.setInstallmentnumber(pt.getInstallmentnumber());
            dto.setPaid(pt.isPaid());
            dto.setCollected(pt.isCollected());
            dto.setCollectorName(collector != null ? collector.getFullName() : null);
            dto.setClientName(client != null ? client.getFullName() : null);
            dto.setResellerName(reseller != null ? reseller.getFullName() : null);

            paymentTransactionForCollectionList.add(dto);
        }

        return paymentTransactionForCollectionList;
    }

    /*
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

     */

}
