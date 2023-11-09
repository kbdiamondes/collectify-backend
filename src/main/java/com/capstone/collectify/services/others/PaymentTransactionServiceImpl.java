package com.capstone.collectify.services.others;


import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionWithClientAndItemDTO;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;


    @Override
    public Iterable<PaymentTransaction> getPaymentTransaction() {
        return paymentTransactionRepository.findAll();
    }

    @Override
    public List<PaymentTransaction> getPaymentTransactionsByClientId(Long clientId) {
        // Implement this method to fetch payment transactions by client ID
        return paymentTransactionRepository.findPaymentTransactionsByClientId(clientId);
    }

    @Override
    public List<PaymentTransactionWithClientAndItemDTO> getPaymentTransactionsWithClientAndItemByResellerId(Long resellerId) {
        List<Object[]> paymentTransactions = paymentTransactionRepository.findPaymentTransactionsWithItemNamesByResellerId(resellerId);
        List<PaymentTransactionWithClientAndItemDTO> transactionsWithClientAndItem = new ArrayList<>();

        for (Object[] row : paymentTransactions) {
            PaymentTransaction pt = (PaymentTransaction) row[0];
            String itemName = (String) row[2];
            String clientName = (String) row[1];

            PaymentTransactionWithClientAndItemDTO dto = new PaymentTransactionWithClientAndItemDTO(pt, itemName, clientName);
            transactionsWithClientAndItem.add(dto);
        }

        return transactionsWithClientAndItem;
    }



    @Override
    public List<PaymentTransactionWithClientAndItemDTO> getUnpaidPaymentTransactionsWithClientAndItemByResellerId(Long resellerId) {
        List<Object[]> unpaidPayments = paymentTransactionRepository.findUnpaidPaymentTransactionsWithNamesAndClientByResellerId(resellerId);
        List<PaymentTransactionWithClientAndItemDTO> transactionsWithNamesAndClient = new ArrayList<>();

        for (Object[] row : unpaidPayments) {
            PaymentTransaction pt = (PaymentTransaction) row[0];
            String itemName = (String) row[2];
            String clientName = (String) row[1];

            PaymentTransactionWithClientAndItemDTO dto = new PaymentTransactionWithClientAndItemDTO(pt, itemName, clientName);
            transactionsWithNamesAndClient.add(dto);
        }

        return transactionsWithNamesAndClient;
    }

    @Override
    public List<PaymentTransactionWithClientAndItemDTO> getUncollectedPaymentTransactionsWithClientAndItemByResellerId(Long resellerId) {
        List<Object[]> uncollectedPayments = paymentTransactionRepository.findUncollectedPaymentTransactionsWithNamesAndClientByResellerId(resellerId);
        List<PaymentTransactionWithClientAndItemDTO> transactionsWithNamesAndClient = new ArrayList<>();

        for (Object[] row : uncollectedPayments) {
            PaymentTransaction pt = (PaymentTransaction) row[0];
            String itemName = (String) row[1];
            String clientName = (String) row[2];

            PaymentTransactionWithClientAndItemDTO dto = new PaymentTransactionWithClientAndItemDTO(pt, itemName, clientName);
            transactionsWithNamesAndClient.add(dto);
        }

        return transactionsWithNamesAndClient;
    }

    @Override
    public List<PaymentTransactionWithClientAndItemDTO> getUncollectedAndUnassignedPaymentTransactionsWithClientAndItemByResellerId(Long resellerId) {
        List<Object[]> uncollectedAndunassignedPayments = paymentTransactionRepository.findUncollectedAndUnassignedPaymentTransactionsWithNamesAndClientByResellerId(resellerId);
        List<PaymentTransactionWithClientAndItemDTO> transactionsWithNamesAndClient = new ArrayList<>();

        for (Object[] row : uncollectedAndunassignedPayments) {
            PaymentTransaction pt = (PaymentTransaction) row[0];
            String itemName = (String) row[1];
            String clientName = (String) row[2];

            PaymentTransactionWithClientAndItemDTO dto = new PaymentTransactionWithClientAndItemDTO(pt, itemName, clientName);
            transactionsWithNamesAndClient.add(dto);
        }

        return transactionsWithNamesAndClient;
    }



}

