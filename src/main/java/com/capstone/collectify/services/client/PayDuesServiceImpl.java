package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

@Service
public class PayDuesServiceImpl implements PayDuesService {

    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public PayDuesServiceImpl(ClientRepository clientRepository, ContractRepository contractRepository) {
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public void payDues(Long clientId, Long contractId, BigDecimal amount) throws AccessDeniedException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getClient().equals(client)) {
            if (!contract.isPaid()) {
                // Check if the amount paid is equal to the due amount
                if (amount.compareTo(contract.getDueAmount()) == 0) {
                    contract.setPaid(true);
                    contractRepository.save(contract);
                } else {
                    throw new IllegalArgumentException("Paid amount is not equal to the due amount.");
                }
            } else {
                throw new IllegalStateException("The contract is already paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to pay dues for this contract.");
        }
    }
}
