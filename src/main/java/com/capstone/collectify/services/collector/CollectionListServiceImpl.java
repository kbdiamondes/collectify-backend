package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionListServiceImpl implements CollectionListService {

    @Autowired
    private CollectorRepository collectorRepository;

    /*
    @Override
    public List<PaymentTransaction> getAssignedUncollectedPaymentsTransactionsForCollector(Long collectorId) {
        return collectorRepository.findUncollectedPaymentTransactionsByCollectorId(collectorId);
    }*/

    @Override
    public List<PaymentTransactionForCollectionListDTO> getAssignedUncollectedPaymentsTransactionsForCollector(Long collectorId) {
        List<PaymentTransaction> unpaidTransactions = collectorRepository.findUncollectedPaymentTransactionsByCollectorId(collectorId);
        List<PaymentTransactionForCollectionListDTO> paymentTransactionForCollectionList = new ArrayList<>();

        for (PaymentTransaction pt : unpaidTransactions) {
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
    @Autowired
    private CollectorRepository collectorRepository;


    @Override
    public List<Contract> getAssignedPaidContractsForCollector(Long collectorId) {
        // Retrieve the collector from the database
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        // Get the collector's assigned paid contracts
        List<Contract> assignedContracts = collector.getAssignedContract();

        // Filter the paid contracts
        List<Contract> paidContracts = assignedContracts.stream()
                .filter(Contract::isPaid)
                .collect(Collectors.toList());

        return paidContracts;
    }

    @Override
    public List<Contract> getAssignedUnpaidContractsForCollector(Long collectorId) {
        // Retrieve the collector from the database
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        // Get the collector's assigned contracts
        List<Contract> assignedContracts = collector.getAssignedContract();

        // Filter the unpaid contracts
        List<Contract> unpaidContracts = assignedContracts.stream()
                .filter(contract -> !contract.isPaid())
                .collect(Collectors.toList());

        return unpaidContracts;
    }


    @Override
    public List<Contract> getAssignedUncollectedContractsForCollector(Long collectorId) {
        // Retrieve the collector from the database
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        // Get the collector's assigned contracts
        List<Contract> assignedContracts = collector.getAssignedContract();

        // Filter the unpaid contracts
        List<Contract> uncollectedContracts = assignedContracts.stream()
                .filter(contract -> !contract.isCollected())
                .collect(Collectors.toList());

        return uncollectedContracts;
    }
*/
}