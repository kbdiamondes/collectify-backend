package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.services.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/collectors")
public class CollectorController {

    @Autowired
    private CollectorService collectorService;

    @PostMapping
    public Collector createCollector(@RequestBody Collector collector) {
        return collectorService.createCollector(collector);
    }

    @GetMapping("/{id}")
    public Optional<Collector> getCollectorById(@PathVariable Long id) {
        return collectorService.getCollectorById(id);
    }

    @PostMapping("/{collectorId}/assign-client/{clientId}")
    public void assignCollectorToClient(@PathVariable Long collectorId, @PathVariable Long clientId) {
        collectorService.assignCollectorToClient(collectorId, clientId);
    }

    // Add other endpoints for Collector-related operations
}

