package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.FileDB;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@Service
public class PayDuesServiceImpl implements PayDuesService {

    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;

    private final FileStorageService fileStorageService;

    @Autowired
    public PayDuesServiceImpl(ClientRepository clientRepository, ContractRepository contractRepository, FileStorageService fileStorageService) {
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void payDues(Long clientId, Long contractId, Map<String, String> amounts, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getClient().equals(client)) {
            if (!contract.isPaid()) {
                // Retrieve the "amount" from the map as a string
                String amountStr = amounts.get("amount");

                // Check if the amount string is valid and parse it to a BigDecimal
                if (amountStr != null && !amountStr.isEmpty()) {
                    BigDecimal amount = new BigDecimal(amountStr);

                    // Check if the amount paid is equal to the due amount
                    if (amount.compareTo(contract.getDueAmount()) == 0) {
                        contract.setPaid(true);

                        // Store the image data and associate it with the contract
                        FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);
                        contract.setTransactionProof(fileDB);

                        contractRepository.save(contract);
                    } else {
                        throw new IllegalArgumentException("Paid amount is not equal to the due amount.");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid amount value.");
                }
            } else {
                throw new IllegalStateException("The contract is already paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to pay dues for this contract.");
        }
    }
}
