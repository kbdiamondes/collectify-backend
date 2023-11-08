package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyCollectorsServiceImpl implements MyCollectorsService {

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Override
    public List<Collector> getCollectorsAssignedByReseller(Long resellerId) {
        List<PaymentTransaction> paymentTransactions = paymentTransactionRepository.findUnpaidPaymentTransactionsByResellerId(resellerId);

        List<Collector> collectors = paymentTransactions.stream()
                .map(PaymentTransaction::getCollector)
                .distinct() // Ensure unique collectors
                .collect(Collectors.toList());

        return collectors;
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