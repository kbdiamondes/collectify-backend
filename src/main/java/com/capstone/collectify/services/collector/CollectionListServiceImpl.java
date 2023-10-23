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

    @Autowired
    private CollectorRepository collectorRepository;

    @Override
    public List<Contract> getAssignedPaidContractsForCollector(Long collectorId) {
        // Retrieve the collector from the database
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        // Get the collector's assigned paid contracts
        List<Contract> assignedContracts = Collections.singletonList(collector.getAssignedContract());

        // Filter the paid contracts
        List<Contract> paidContracts = assignedContracts.stream()
                .filter(Contract::isPaid)
                .collect(Collectors.toList());

        return paidContracts;
    }
}