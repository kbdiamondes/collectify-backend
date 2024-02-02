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
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Collector createCollector(Collector collector) {
        String rawPassword = collector.getPassword();

        String encodedPassword = passwordEncoder.encode(rawPassword);

        collector.setPassword(encodedPassword);

        collectorRepository.save(collector);

        return collector;
    }

    @Override
    public Optional<Collector> getCollectorById(Long id) {
        return collectorRepository.findById(id);
    }

    public Iterable<Collector> getCollector() {
        return collectorRepository.findAll();
    }


    @Override
    public int getTotalAssignedPaymentTransactions(Long collectorId) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);

        if (collector != null) {
            return collector.getAssignedPaymentTransactions().size();
        } else {
            return 0;
        }
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
                collector.setPassword(passwordEncoder.encode(password));
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

