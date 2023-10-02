package com.capstone.collectify.services;

import com.capstone.collectify.models.Collector;

import java.util.Optional;

public interface CollectorService {
    void assignCollectorToClient(Long collectorId, Long clientId);

    Collector createCollector(Collector collector);

    Optional<Collector> getCollectorById(Long id);

    Iterable<Collector> getCollector();

}

