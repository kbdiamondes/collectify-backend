package com.capstone.collectify.services;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResellerServiceImpl implements ResellerService {

    @Autowired
    private ResellerRepository resellerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private CollectionHistoryRepository collectionHistoryRepository;

    @Override
    public Contract createContract(Long resellerId, String clientUsername, String username, String itemName, BigDecimal dueAmount, Long fullPrice, Boolean isPaid) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with username: " + username));

        Contract contract = new Contract();
        contract.setReseller(reseller);
        contract.setClient(client);
        contract.setUsername(clientUsername);
        contract.setItemName(itemName);
        contract.setDueAmount(dueAmount);
        contract.setFullPrice(fullPrice);
        contract.setPaid(isPaid);

        // Save the contract and return it
        return contractRepository.save(contract);
    }

    @Override
    public void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getReseller().equals(reseller)) {
            Collector collector = collectorRepository.findById(collectorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

            contract.setCollector(collector);
            contractRepository.save(contract);
        } else {
            throw new AccessDeniedException("You don't have permission to assign a collector to this contract.");
        }
    }

    @Override
    public void collectPayment(Long resellerId, Long contractId, BigDecimal amount) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getReseller().equals(reseller)) {
            // Check if the collected amount is less than or equal to the due amount
            if (amount.compareTo(contract.getDueAmount()) <= 0) {
                // Update the due amount and mark the contract as paid if necessary
                contract.setDueAmount(contract.getDueAmount().subtract(amount));
                if (contract.getDueAmount().compareTo(BigDecimal.ZERO) == 0) {
                    contract.setPaid(true);
                }
                contractRepository.save(contract);

                // Record the collection history
                CollectionHistory history = new CollectionHistory();
                history.setCollectedAmount(amount);
                history.setCollectionDate(LocalDateTime.now());
                history.setReseller(reseller);
                collectionHistoryRepository.save(history);

                System.out.println(amount + "is successfully collected");
            } else {
                throw new IllegalArgumentException("Collected amount exceeds the due amount.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this contract.");
        }
    }

    @Override
    public List<CollectionHistory> getCollectionHistory(Long resellerId) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        return collectionHistoryRepository.findByReseller(reseller);
    }

    @Override
    public Reseller createReseller(Reseller reseller) {
        return resellerRepository.save(reseller);
    }

    @Override
    public Optional<Reseller> getResellerById(Long id) {
        return resellerRepository.findById(id);
    }

    @Override
    public Iterable<Reseller> getReseller() {
        return resellerRepository.findAll();
    }

    @Override
    public Collector getAssignedCollector(Long resellerId, Long contractId) {
        // Step 1: Retrieve the Reseller
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        // Step 2: Retrieve the Contract
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        // Step 3: Get the Assigned Collector
        Collector assignedCollector = contract.getCollector();

        // Step 4: Return the Assigned Collector
        return assignedCollector;
    }

    private final String apiUrl = "https://tamworth-wallaby-raqd.2.sg-1.fl0.io/distributor/getAllDistributor";
    public void fetchDataAndSaveToDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Reseller[] resellers = restTemplate.getForObject(apiUrl, Reseller[].class);

        if (resellers != null) {
            for (Reseller reseller : resellers) {
                // Extract relevant fields from the JSON response
                String firstname = reseller.getFirstname();
                String middlename = reseller.getMiddlename();
                String lastname = reseller.getLastname();
                String address = reseller.getAddress();

                // Concatenate first, middle, and last names into the fullName field
                StringBuilder fullNameBuilder = new StringBuilder();
                if (firstname != null) {
                    fullNameBuilder.append(firstname);
                }
                if (middlename != null) {
                    if (fullNameBuilder.length() > 0) {
                        fullNameBuilder.append(" ");
                    }
                    fullNameBuilder.append(middlename);
                }
                if (lastname != null) {
                    if (fullNameBuilder.length() > 0) {
                        fullNameBuilder.append(" ");
                    }
                    fullNameBuilder.append(lastname);
                }
                String fullName = fullNameBuilder.toString();

                // Create a Reseller instance and set the extracted data
                Reseller newReseller = new Reseller();
                newReseller.setUsername(firstname != null && lastname != null ? firstname + "." + lastname : "");
                newReseller.setFirstname(firstname);
                newReseller.setMiddlename(middlename);
                newReseller.setLastname(lastname);
                newReseller.setFullName(fullName);
                newReseller.setEmail((firstname != null && lastname != null) ? (firstname.toLowerCase() + lastname.toLowerCase() + "@example.com") : "");
                newReseller.setPassword(lastname != null ? (lastname + "123") : "");
                newReseller.setAddress(address);

                // Check if the reseller already exists in the database using some unique identifier (e.g., username or email)
                // If it doesn't exist, save it to the database
                if (!resellerRepository.existsByUsername(newReseller.getUsername())
                        && !resellerRepository.existsByEmail(newReseller.getEmail())) {
                    resellerRepository.save(newReseller);
                }
            }
        }
    }

    // This method will run automatically every 5 minutes
    @Scheduled(fixedRate = 350000) // 5 minutes = 300,000 milliseconds
    public void scheduleFetchAndSave() {
        fetchDataAndSaveToDatabase();
    }


}

/*
v2
@Override
    public Contract createContract(Long resellerId,  Long clientId, String username, String itemName, BigDecimal dueAmount, Long fullPrice, Boolean isPaid) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + username));

        Contract contract = new Contract();
        contract.setReseller(reseller);
        contract.setClient(client);
        contract.setUsername(username);
        contract.setItemName(itemName);
        contract.setDueAmount(dueAmount);
        contract.setFullPrice(fullPrice);
        contract.setPaid(isPaid);

        // Save the contract and return it
        return contractRepository.save(contract);
    }
 */