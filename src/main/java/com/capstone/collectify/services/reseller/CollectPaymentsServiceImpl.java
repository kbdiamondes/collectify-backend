package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CollectPaymentsServiceImpl implements CollectPaymentsService {

    private final ResellerRepository resellerRepository;
    private final ContractRepository contractRepository;
    private final CollectionHistoryRepository collectionHistoryRepository;

    private final FileStorageService fileStorageService;

    @Autowired
    public CollectPaymentsServiceImpl(
            ResellerRepository resellerRepository,
            ContractRepository contractRepository,
            CollectionHistoryRepository collectionHistoryRepository, FileStorageService fileStorageService) {
        this.resellerRepository = resellerRepository;
        this.contractRepository = contractRepository;
        this.collectionHistoryRepository = collectionHistoryRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void collectPayments(Long resellerId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));
        Client client = contract.getClient(); // Get the associated client
        Collector collector = contract.getCollector(); // Get the associated collector

        // Check if the reseller is associated with the contract
        if (contract.getReseller().equals(reseller)) {
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
                    history.setReseller(reseller);
                    history.setClient(client);
                    history.setCollector(collector);
                    history.setPaymentType(paymentType);

                    // Store the image data and associate it with the contract
                    FileDB fileDB = fileStorageService.store(base64ImageData,fileName, contentType);
                    history.setTransactionProof(fileDB);

                    collectionHistoryRepository.save(history);

                    System.out.println(amountToCollect + " is successfully collected");
                } else {
                    throw new IllegalStateException("The contract has already been paid.");
                }
            } else {
                throw new IllegalStateException("The contract is not yet paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this contract.");
        }
    }

    @Override
    public void collectPaymentsFromAllContracts(Long resellerId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        // Get all contracts assigned to the reseller that do not have a Collector assigned
        List<Contract> contracts = contractRepository.findContractsForResellerWithNoCollector(resellerId);

        for (Contract contract : contracts) {
            BigDecimal amountToCollect = contract.getDueAmount();

            if (amountToCollect != null && amountToCollect.compareTo(BigDecimal.ZERO) > 0) {
                // Update the due amount and mark the contract as paid if necessary
                contract.setDueAmount(BigDecimal.ZERO);
                contract.setPaid(true);
                contract.setCollected(true);
                contractRepository.save(contract);

                // Record the collection history
                CollectionHistory history = new CollectionHistory();
                history.setCollectedAmount(amountToCollect);
                history.setCollectionDate(LocalDateTime.now());
                history.setReseller(reseller);
                history.setClient(contract.getClient());

                // Store the image data and associate it with the contract
                FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);
                history.setTransactionProof(fileDB);
                history.setPaymentType(paymentType);

                collectionHistoryRepository.save(history);

                System.out.println(amountToCollect + " is successfully collected for contract " + contract.getContract_id());
            } else {
                throw new IllegalStateException("The contract has already been paid or the due amount is null.");
            }
        }
    }

}
/*

version 1
  @Override
    public void collectPayments(Long resellerId, Long contractId, String paymentType) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        // Check if the reseller is associated with the contract
        if (contract.getReseller().equals(reseller)) {
            BigDecimal amountToCollect = contract.getDueAmount();

            if (amountToCollect.compareTo(BigDecimal.ZERO) > 0) {
                // Update the due amount and mark the contract as paid if necessary
                contract.setDueAmount(BigDecimal.ZERO);
                contract.setPaid(true);
                contractRepository.save(contract);

                // Record the collection history
                CollectionHistory history = new CollectionHistory();
                history.setCollectedAmount(amountToCollect);
                history.setCollectionDate(LocalDateTime.now());
                history.setReseller(reseller);
                history.setPaymentType(paymentType);
                collectionHistoryRepository.save(history);

                System.out.println(amountToCollect + " is successfully collected");
            } else {
                throw new IllegalStateException("The contract has already been paid.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this contract.");
        }
 */
