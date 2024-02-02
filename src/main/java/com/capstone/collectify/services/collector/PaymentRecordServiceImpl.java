package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentRecordServiceImpl implements PaymentRecordService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CollectorRepository collectorRepository; // Add this

    @Override
    public List<PaymentTransactionForCollectionListDTO> getCollectedPaymentsForCollector(Long collectorId) {
        List<PaymentTransaction> collectedTransactions = collectorRepository.findCollectedPaymentsByCollectorId(collectorId);
        List<PaymentTransactionForCollectionListDTO> paymentTransactionForCollectionList = new ArrayList<>();

        for (PaymentTransaction pt : collectedTransactions) {
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
    public List<Contract> getPaymentRecordsForCollector(Long collectorId) {
        // Retrieve the Collector entity by ID
        Collector collector = collectorRepository.findById(collectorId).orElse(null);

        if (collector != null) {
            // Retrieve contracts assigned to the collector with dueAmount = 0
            return contractRepository.findContractsForCollectorWithDueAmount(collectorId, BigDecimal.ZERO);
        }

        return new ArrayList<>();
    }

     */
}

