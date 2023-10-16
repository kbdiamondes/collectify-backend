package com.capstone.collectify.services;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public void payDue(Long clientId, Long contractId, BigDecimal amount) throws AccessDeniedException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getClient().equals(client)) {
            if (!contract.isPaid()) {
                // Check if the amount paid is equal to or greater than the due amount
                if (amount.compareTo(contract.getDueAmount()) >= 0) {
                    contract.setPaid(true);
                    contract.setDueAmount(BigDecimal.ZERO);
                    contractRepository.save(contract);
                } else {
                    throw new IllegalArgumentException("Paid amount is less than the due amount.");
                }
            } else {
                throw new IllegalStateException("The contract is already paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to pay dues for this contract.");
        }
        System.out.println("Success");

    }

    @Override
    public List<Contract> getClientContracts(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        return contractRepository.findByClient(client);
    }


    // Create user
    public Client createClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    // Get users
    public Iterable<Client> getClient() {
        return clientRepository.findAll();
    }


    // Delete user
    public ResponseEntity deleteClient(Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }

    // Update user
    public ResponseEntity updateClient(Long id, Client user) {
        Client clientForUpdating = clientRepository.findById(id).get();

        clientForUpdating.setUsername(user.getUsername());
        clientForUpdating.setPassword(user.getPassword());
        clientRepository.save(clientForUpdating);
        return new ResponseEntity<>("Client details updated successfully", HttpStatus.OK);

    }

    //    Find user by username
    public Optional<Optional<Client>> findByUsername(String username){
//        if findByUsername method returns null it will throw a NullPointerException.
//        using .ofNullable method will avoid this from happening.
        return Optional.ofNullable(clientRepository.findByUsername(username));
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Contract createContractForClient(Long clientId, Contract contract) {
        return null;
    }


    private final String apiUrl = "https://tamworth-wallaby-raqd.2.sg-1.fl0.io/dealer/getAllDealers";
    public void fetchDataAndSaveToDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Client[] clients = restTemplate.getForObject(apiUrl, Client[].class);

        if (clients != null) {
            for (Client client : clients) {

                // Extract client data
                String firstname = client.getFirstname();
                String middlename = client.getMiddlename();
                String lastname = client.getLastname();
                String address = client.getAddress();

                // Concatenate first, middle, and last names into the fullName field
                String userName = firstname + "." + lastname;
                String password = lastname + "123";
                String fullName = firstname + " " + middlename + " " + lastname;
                String email = firstname.toLowerCase() + lastname.toLowerCase() + "@example.com";
                String clientAddress = address;

                client.setUsername(userName);
                client.setFullName(fullName);
                client.setEmail(email);
                client.setPassword(password);
                client.setAddress(clientAddress);

                // Check if the client already exists in the database using some unique identifier (e.g., username or email)
                // If it doesn't exist, save it to the database
                if (!clientRepository.existsByUsername(client.getUsername())
                        && !clientRepository.existsByEmail(client.getEmail())) {
                    clientRepository.save(client);
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

