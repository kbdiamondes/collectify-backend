package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public void assignCollectorToClient(Long collectorId, Long clientId) {
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        // Create a new Contract and set its properties
        Contract contract = new Contract();
        contract.setClient(client);
        contract.setCollector(collector);
        contract.setDueAmount(BigDecimal.ZERO); // Set your desired default values

        // Save the Contract first to ensure that it gets a valid ID
        contractRepository.save(contract);

        // Update the Collector with the assigned Contract
        collector.setAssignedContract(contract);

        // Save the Collector
        collectorRepository.save(collector);
    }

    @Override
    public Collector createCollector(Collector collector) {
        return collectorRepository.save(collector);
    }

    @Override
    public Optional<Collector> getCollectorById(Long id) {
        return collectorRepository.findById(id);
    }

    public Iterable<Collector> getCollector() {
        return collectorRepository.findAll();
    }

}

