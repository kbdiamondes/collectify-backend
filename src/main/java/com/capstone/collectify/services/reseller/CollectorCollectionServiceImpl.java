package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class CollectorCollectionServiceImpl implements CollectorCollectionService{
    private final ResellerRepository resellerRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final CollectorRepository collectorRepository;

    @Autowired
    public CollectorCollectionServiceImpl(
            ResellerRepository resellerRepository,
            PaymentTransactionRepository paymentTransactionRepository,
            CollectorRepository collectorRepository) {
        this.resellerRepository = resellerRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.collectorRepository = collectorRepository;
    }

    @Override
    public void assignCollector(Long resellerId, Long paymentTransactionId, Long collectorId) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(paymentTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment transaction not found with id: " + paymentTransactionId));


        if (paymentTransaction.getContract().getReseller().equals(reseller)) {
            Collector collector = collectorRepository.findById(collectorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

            paymentTransaction.setCollector(collector);
            collector.getResellers().add(reseller);
            collectorRepository.save(collector);
            paymentTransactionRepository.save(paymentTransaction);
        } else {
            throw new AccessDeniedException("You don't have permission to assign a collector to this payment transaction.");
        }
    }
}
