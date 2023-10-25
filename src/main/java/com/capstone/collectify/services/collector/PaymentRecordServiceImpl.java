package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentRecordServiceImpl implements PaymentRecordService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CollectorRepository collectorRepository; // Add this

    @Override
    public List<Contract> getPaymentRecordsForCollector(Long collectorId) {
        // Retrieve the Collector entity by ID
        Collector collector = collectorRepository.findById(collectorId).orElse(null);

        if (collector != null) {
            // Retrieve contracts assigned to the collector with dueAmount = 0
            return contractRepository.findContractsForCollectorWithDueAmount(collectorId, BigDecimal.ZERO);
        }

        return new ArrayList<>();
    }
}

