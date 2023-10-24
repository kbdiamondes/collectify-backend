package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class CollectorCollectionServiceImpl implements CollectorCollectionService{

    private final ResellerRepository resellerRepository;
    private final ContractRepository contractRepository;
    private final CollectorRepository collectorRepository;

    @Autowired
    public CollectorCollectionServiceImpl(
            ResellerRepository resellerRepository,
            ContractRepository contractRepository,
            CollectorRepository collectorRepository) {
        this.resellerRepository = resellerRepository;
        this.contractRepository = contractRepository;
        this.collectorRepository = collectorRepository;
    }

    @Override
    public void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));


        // Check if the reseller is associated with the contract
        if (contract.getReseller().equals(reseller)) {
            Collector collector = collectorRepository.findById(collectorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));
            collector.getAssignedContract().add(contract);
            contract.setCollector(collector);
            contractRepository.save(contract);
        } else {
            throw new AccessDeniedException("You don't have permission to assign a collector to this contract.");
        }
    }
}
