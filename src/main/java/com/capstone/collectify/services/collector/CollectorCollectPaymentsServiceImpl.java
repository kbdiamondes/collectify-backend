package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.FileDB;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
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
public class CollectorCollectPaymentsServiceImpl implements CollectorCollectPaymentsService {

    private final CollectorRepository collectorRepository;
    private final ContractRepository contractRepository;
    private final CollectionHistoryRepository collectionHistoryRepository;

    private final FileStorageService fileStorageService;

    @Autowired
    public CollectorCollectPaymentsServiceImpl(
            CollectorRepository collectorRepository,
            ContractRepository contractRepository,
            CollectionHistoryRepository collectionHistoryRepository,
            FileStorageService fileStorageService) {
        this.collectorRepository = collectorRepository;
        this.contractRepository = contractRepository;
        this.collectionHistoryRepository = collectionHistoryRepository;
        this.fileStorageService = fileStorageService;
    }

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

}

