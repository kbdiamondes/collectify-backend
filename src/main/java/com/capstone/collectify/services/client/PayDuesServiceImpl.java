package com.capstone.collectify.services.client;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.TransactionHistoryRepository;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PayDuesServiceImpl implements PayDuesService {
    /*
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final CollectionHistoryRepository collectionHistoryRepository;
    private final FileStorageService fileStorageService;

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    public PayDuesServiceImpl(
            ClientRepository clientRepository,
            ContractRepository contractRepository,
            CollectionHistoryRepository collectionHistoryRepository,
            FileStorageService fileStorageService, TransactionHistoryRepository transactionHistoryRepository) {
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
        this.collectionHistoryRepository = collectionHistoryRepository;
        this.fileStorageService = fileStorageService;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }


    @Override
    public void payDues(Long clientId, Long contractId, Map<String, String> amounts, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        Reseller reseller = contract.getReseller(); // Retrieve the reseller associated with the contract

        // Fetch the reseller's name
        String resellerName = reseller.getFullName(); // Replace this with the actual field where the reseller's name is stored

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

                        // Set the lastPaymentDate to the current date and time
                        contract.setLastPaymentDate(LocalDateTime.now());

                        // Create a new payment history record
                        TransactionHistory transactionHistoryRecord = new TransactionHistory();
                        transactionHistoryRecord.setAmountPaid(amount);
                        transactionHistoryRecord.setPaymentDate(LocalDateTime.now());

                        transactionHistoryRecord.setClientName(client.getFullName());
                        // Set the reseller name in the transaction history
                        transactionHistoryRecord.setResellerName(resellerName);
                        transactionHistoryRecord.setOrderId(contract.getOrderid());
                        transactionHistoryRecord.setProductName(contract.getItemName());
                        transactionHistoryRecord.setContract(contract);
                        // Add the payment history record to the client's history
                        client.addPaymentHistory(transactionHistoryRecord);

                        // Save the payment history record to the database
                        transactionHistoryRepository.save(transactionHistoryRecord);

                        // Store the image data and associate it with the contract
                        FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);
                        contract.setTransactionProof(fileDB);
                        transactionHistoryRecord.setTransactionProof(fileDB);

                        // Save the contract, client, and payment history
                        contractRepository.save(contract);
                        clientRepository.save(client);

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
*/


    /*
    @Override
    public void processMonthlyPayments() {
        // Get the current date and time
        LocalDateTime currentDate = LocalDateTime.now();

        // Iterate through contracts with isMonthly=true and calculate monthly payments
        List<Contract> monthlyPaymentContracts = contractRepository.findByIsMonthly(true);

        for (Contract contract : monthlyPaymentContracts) {
            if (!contract.isPaid()) {
                // Calculate the next payment date based on the contract's payment plan
                LocalDateTime lastPaymentDate = contract.getLastPaymentDate();
                int installmentDuration = contract.getInstallmentDuration();
                LocalDateTime nextPaymentDate = lastPaymentDate.plusMonths(1);

                // Check if the current date is the next payment date
                if (currentDate.toLocalDate().isEqual(nextPaymentDate.toLocalDate())) {
                    // Calculate the monthly payment amount
                    BigDecimal monthlyInstallmentAmount = contract.getDueAmount().divide(BigDecimal.valueOf(installmentDuration), 2, RoundingMode.HALF_UP);

                    // Check if the client has sufficient balance
                    if (contract.getDueAmount().compareTo(monthlyInstallmentAmount) >= 0) {
                        // Update the due amount and mark the contract as paid if necessary
                        contract.setDueAmount(contract.getDueAmount().subtract(monthlyInstallmentAmount));
                        if (contract.getDueAmount().compareTo(BigDecimal.ZERO) == 0) {
                            contract.setPaid(true);
                        }

                        // Record the collection history (you can customize this part)
                        CollectionHistory history = new CollectionHistory();
                        history.setCollectedAmount(monthlyInstallmentAmount);
                        history.setCollectionDate(currentDate);
                        history.setClient(contract.getClient());
                        // You can also set other relevant attributes in the history

                        // Save the contract and collection history
                        contract.setLastPaymentDate(currentDate);
                        contractRepository.save(contract);
                        collectionHistoryRepository.save(history);

                        System.out.println("Payment collected: " + monthlyInstallmentAmount + " for contract: " + contract.getContract_id());
                    } else {
                        // Handle insufficient balance (e.g., send notifications)
                        // You can implement this part based on your business logic
                    }
                }
            }
        }
    }


     */
}

