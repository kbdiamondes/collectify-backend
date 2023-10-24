package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        collector.getAssignedContract().add(contract);

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

    @Value("${api.endpoint.getEmployees}")
    private String apiUrl;

    public void fetchDataAndSaveToDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Collector[] collectors = restTemplate.getForObject(apiUrl, Collector[].class);

        if (collectors != null) {
            for (Collector collector : collectors) {
                // Extract first, middle, and last names from JSON response
                String firstname = collector.getFirstname();
                String middlename = collector.getMiddlename();
                String lastname = collector.getLastname();
                String address = collector.getAddress();


                // Concatenate first, middle, and last names into the fullName field
                String userName = firstname+"."+lastname;
                String password = lastname+"123";
                String fullName = firstname + " " + middlename + " " + lastname;
                String email = firstname + lastname + "@gmail.com";
                String collectorAddress = address;

                collector.setUsername(userName);
                collector.setFullName(fullName);
                collector.setEmail(email);
                collector.setPassword(password);
                collector.setAddress(collectorAddress);

                // Check if the collector already exists in the database using some unique identifier (e.g., username or email)
                // If it doesn't exist, save it to the database
                if (!collectorRepository.existsByUsername(collector.getUsername())
                        && !collectorRepository.existsByEmail(collector.getEmail())) {
                    collectorRepository.save(collector);
                }
            }
        }
    }

    // This method will run automatically every 5 minutes
    @Scheduled(fixedRate = 5000) // 5 minutes = 300,000 milliseconds
    public void scheduleFetchAndSave() {
        fetchDataAndSaveToDatabase();
    }

}

