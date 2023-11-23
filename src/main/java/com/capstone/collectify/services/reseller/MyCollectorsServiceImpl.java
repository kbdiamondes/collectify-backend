package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyCollectorsServiceImpl implements MyCollectorsService {

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Override
    public List<PaymentTransaction> getAssignedPaymentTransactionsByReseller(Long resellerId) {
        return paymentTransactionRepository.findAssignedPaymentTransactionsByResellerId(resellerId);
    }

    @Override
    public List<PaymentTransactionWithCollectorDTO> getAssignedPaymentTransactionsByResellerWithCollectorName(Long resellerId) {
        List<PaymentTransaction> assignedPaymentTransactions = paymentTransactionRepository.findAssignedPaymentTransactionsByResellerId(resellerId);
        List<PaymentTransactionWithCollectorDTO> paymentTransactionWithCollectors = new ArrayList<>();

        for (PaymentTransaction pt : assignedPaymentTransactions) {
            String collectorName = pt.getCollector().getFullName(); // Assuming there's a method to get the collector's full name

            PaymentTransactionWithCollectorDTO dto = new PaymentTransactionWithCollectorDTO();
            dto.setPayment_transactionid(pt.getPayment_transactionid());
            dto.setOrderid(pt.getOrderid());
            dto.setPaymenttransactionid(pt.getPaymenttransactionid());
            dto.setAmountdue(pt.getAmountdue());
            dto.setStartingdate(pt.getStartingdate());
            dto.setEnddate(pt.getEnddate());
            dto.setInstallmentnumber(pt.getInstallmentnumber());
            dto.setPaid(pt.isPaid());
            dto.setCollected(pt.isCollected());
            dto.setCollectorName(collectorName);

            paymentTransactionWithCollectors.add(dto);
        }

        return paymentTransactionWithCollectors;
    }

    /*
    @Override
    public List<Collector> getCollectorsAssignedByReseller(Long resellerId) {
        return collectorRepository.findCollectorsAssignedByReseller(resellerId);
    }

    @Override
    public List<Contract> getContractsAssignedToCollectorByReseller(Long resellerId, Long collectorId) {
        return contractRepository.findContractsAssignedToCollectorByReseller(resellerId, collectorId);
    }

     */
}