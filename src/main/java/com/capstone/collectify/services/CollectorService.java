package com.capstone.collectify.services;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;

import java.util.List;
import java.util.Optional;

public interface CollectorService {
    Collector createCollector(Collector collector);

    Optional<Collector> getCollectorById(Long id);

    Iterable<Collector> getCollector();

    int getTotalAssignedPaymentTransactions(Long collectorId);

}

