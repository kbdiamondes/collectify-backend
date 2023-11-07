package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.CollectorRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionListServiceImpl implements CollectionListService {
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