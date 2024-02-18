package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CollectorCollectPaymentsServiceImpl implements CollectorCollectPaymentsService {

    private final CollectorRepository collectorRepository;
    private final ContractRepository contractRepository;
    private final CollectionHistoryRepository collectionHistoryRepository;

    private final FileStorageService fileStorageService;

    private final PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    public CollectorCollectPaymentsServiceImpl(
            CollectorRepository collectorRepository,
            ContractRepository contractRepository,
            CollectionHistoryRepository collectionHistoryRepository,
            FileStorageService fileStorageService, PaymentTransactionRepository paymentTransactionRepository) {
        this.collectorRepository = collectorRepository;
        this.contractRepository = contractRepository;
        this.collectionHistoryRepository = collectionHistoryRepository;
        this.fileStorageService = fileStorageService;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @Override
    public void collectPayments(Long collectorId, Long paymentTransactionId, String paymentType, String base64ImageData, String fileName, String contentType)
            throws AccessDeniedException, IOException {

        // Fetch collector and payment transaction from repositories
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(paymentTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Transaction not found with id: " + paymentTransactionId));

        // Check if the collector is assigned to the payment transaction
        if (paymentTransaction.getCollector().equals(collector)) {
            // Ensure that the payment transaction is not yet paid
            if (paymentTransaction.isPaid()) {
                // Check if the payment transaction is not collected and the collector matches
                if (paymentTransaction.getCollector().getCollector_id().equals(collectorId) && !paymentTransaction.isCollected()) {
                    // Save the file to the storage service
                    paymentTransaction.setCollectiondate(LocalDate.now());
                    FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);
                    // Update the payment transaction as collected
                    paymentTransaction.setCollected(true);
                    paymentTransactionRepository.save(paymentTransaction);

                    // Create and save the collection history
                    CollectionHistory history = createCollectionHistory(paymentTransaction, collector, paymentType, fileDB);
                    collectionHistoryRepository.save(history);

                    System.out.println("Payment collected successfully");
                } else {
                    throw new IllegalStateException("The payment transaction is already collected");
                }
            } else {
                throw new IllegalStateException("The payment transaction is not yet paid");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this transaction.");
        }
    }

    @Override
    public void collectPaymentsFromAllTransactions(Long collectorId, String paymentType, String base64ImageData, String fileName, String contentType)
            throws AccessDeniedException, IOException {

        // Fetch collector from repository
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        List<PaymentTransaction> assignedPaymentTransactions = paymentTransactionRepository.findByCollectorAndIsPaidAndIsCollected(collector, true, false);

        for (PaymentTransaction paymentTransaction : assignedPaymentTransactions) {
            // Check if the payment transaction is assigned to the collector
            if (paymentTransaction.getCollector().equals(collector)) {
                // Ensure that the payment transaction is paid and not yet collected
                if (paymentTransaction.isPaid() && !paymentTransaction.isCollected()) {
                    // Save the file to the storage service
                    paymentTransaction.setCollectiondate(LocalDate.now());
                    FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);

                    // Update the payment transaction as collected
                    paymentTransaction.setCollected(true);
                    paymentTransactionRepository.save(paymentTransaction);

                    // Create and save the collection history
                    CollectionHistory history = createCollectionHistory(paymentTransaction, collector, paymentType, fileDB);
                    collectionHistoryRepository.save(history);

                    System.out.println("Collected all payments successfully!");

                } else {
                    throw new IllegalStateException("Payment collection failed: The payment transaction is already collected or has not been paid.");
                }
            } else {
                throw new AccessDeniedException("You don't have permission to collect payment for this transaction.");
            }
        }
    }


    private CollectionHistory createCollectionHistory(PaymentTransaction paymentTransaction, Collector collector, String paymentType, FileDB fileDB) {
        CollectionHistory history = new CollectionHistory();
        history.setCollectedAmount(BigDecimal.valueOf(paymentTransaction.getAmountdue()));
        history.setCollectionDate(LocalDateTime.now());
        history.setCollector(collector);
        history.setPaymentType(paymentType);
        history.setTransactionProof(fileDB);

        // Set additional fields for history entity
        if (paymentTransaction.getContract().getOrderid() != null) {
            history.setOrderid(paymentTransaction.getContract().getOrderid());
        }

        if (paymentTransaction.getContract().getItemName() != null) {
            history.setItemName(paymentTransaction.getContract().getItemName());
        }

        if (paymentTransaction.getContract().getReseller().getFullName() != null) {
            history.setResellerName(paymentTransaction.getContract().getReseller().getFullName());
        }

        if (paymentTransaction.getContract().getClient().getFullName() != null) {
            history.setClientName(paymentTransaction.getContract().getClient().getFullName());
        }

        if (paymentTransaction.getCollector() != null && paymentTransaction.getCollector().getFullName() != null) {
            history.setCollectorName(paymentTransaction.getCollector().getFullName());
        }

        return history;
    }


    /*
    @Override
    public void collectPayments(Long collectorId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        // Check if the collector is associated with the contract
        if (contract.getCollector().equals(collector)) {
            BigDecimal amountToCollect = contract.getDueAmount();

            if (amountToCollect.compareTo(BigDecimal.ZERO) > 0) {
                // Update the due amount and mark the contract as paid if necessary
                contract.setDueAmount(BigDecimal.ZERO);
                contract.setPaid(true);
                contract.setCollected(true);
                contractRepository.save(contract);

                // Record the collection history
                CollectionHistory history = new CollectionHistory();
                history.setCollectedAmount(amountToCollect);
                history.setCollectionDate(LocalDateTime.now());
                history.setCollector(collector);
                history.setPaymentType(paymentType);

                if (contract.getItemName() != null) {
                    history.setItemName(contract.getItemName());
                }

                if (contract.getCollector() != null && contract.getCollector().getUsername() != null) {
                    history.setCollector_username(contract.getCollector().getUsername());
                }

                if (contract.getUsername() != null) {
                    history.setClient_username(contract.getUsername());
                }

                if (contract.getReseller() != null && contract.getReseller().getUsername() != null) {
                    history.setReseller_username(contract.getReseller().getUsername());
                }


                // Store the image data and associate it with the contract
                FileDB fileDB = fileStorageService.store(base64ImageData,fileName, contentType);
                history.setTransactionProof(fileDB);

                collectionHistoryRepository.save(history);

                System.out.println(amountToCollect + " is successfully collected");
            } else {
                throw new IllegalStateException("The contract has already been paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this contract.");
        }
    }

    @Override
    public void collectPaymentsFromAllContracts(Long collectorId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        // Get all contracts assigned to the collector
        List<Contract> contracts = collector.getAssignedContract();

        for (Contract contract : contracts) {
            // Check if the contract is paid
            if (contract.isPaid()) {
                BigDecimal amountToCollect = contract.getDueAmount();

                if (amountToCollect.compareTo(BigDecimal.ZERO) > 0) {
                    // Update the due amount and mark the contract as paid if necessary
                    contract.setDueAmount(BigDecimal.ZERO);
                    contract.setPaid(true);
                    contract.setCollected(true);
                    contractRepository.save(contract);

                    // Record the collection history
                    CollectionHistory history = new CollectionHistory();
                    history.setCollectedAmount(amountToCollect);
                    history.setCollectionDate(LocalDateTime.now());
                    history.setCollector(collector);
                    history.setPaymentType(paymentType);
                    history.setClient(contract.getClient());
                    history.setReseller(contract.getReseller());
                    history.setItemName(contract.getItemName());
                    history.setReseller_username(contract.getReseller().getUsername());
                    history.setCollector_username(contract.getCollector().getUsername());
                    history.setClient_username(contract.getUsername());


                    // Store the image data and associate it with the contract
                    FileDB fileDB = fileStorageService.store(base64ImageData,fileName, contentType);
                    history.setTransactionProof(fileDB);

                    collectionHistoryRepository.save(history);

                    System.out.println(amountToCollect + " is successfully collected");
                } else {
                    throw new IllegalStateException("The contract has already been paid.");
                }
            } else {
                throw new AccessDeniedException("You don't have permission to collect payment for this contract or \n Contract has not been paid");
            }
        }
    }
*/
}

