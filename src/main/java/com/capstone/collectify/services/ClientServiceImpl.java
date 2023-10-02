package com.capstone.collectify.services;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public Optional<Client> findByUsername(String username){
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

}

