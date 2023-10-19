package com.capstone.collectify.controllers.reseller;


import com.capstone.collectify.services.reseller.CollectorCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@RestController
@CrossOrigin
@RequestMapping("/assigncollectors")
public class CollectorCollectionController {

    private final CollectorCollectionService collectorAssignmentService;

    @Autowired
    public CollectorCollectionController(CollectorCollectionService collectorAssignmentService) {
        this.collectorAssignmentService = collectorAssignmentService;
    }

    @PostMapping("/{resellerId}/contracts/{contractId}/assign-collector")
    public ResponseEntity<String> assignCollector(
            @PathVariable Long resellerId,
            @PathVariable Long contractId,
            @RequestParam Long collectorId) {
        try {
            collectorAssignmentService.assignCollector(resellerId, contractId, collectorId);

            return ResponseEntity.ok("Collector Assigned Successfully!");
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

}
