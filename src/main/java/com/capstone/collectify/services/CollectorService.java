package com.capstone.collectify.services;

import com.capstone.collectify.models.Collector;
import org.springframework.http.ResponseEntity;

public interface CollectorService {

    // Create Client
    void createCollector(Collector client);

    // Get Client
    Iterable<Collector> getUsername();

    // Delete Client
    ResponseEntity deleteCollector(Long id);

    // Update a Client
    ResponseEntity updateCollector(Long id, Collector collector);
}