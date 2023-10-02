package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void assignCollectorToClient(Long collectorId, Long clientId) {
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        // Check if the collector is already assigned to another client
        if (collector.getAssignedClient() != null) {
            throw new IllegalStateException("Collector is already assigned to a client.");
        }

        collector.setAssignedClient(client);
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

