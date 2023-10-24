package com.capstone.collectify.controllers.collector;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.CollectorService;
import com.capstone.collectify.services.collector.CollectionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionListController {

    @Autowired
    private CollectionListService collectionListService;

    @GetMapping("/{collectorId}/assigned-paid-contracts")
    public ResponseEntity<List<Contract>> getAssignedPaidContractsForCollector(@PathVariable Long collectorId) {
        List<Contract> paidContracts = collectionListService.getAssignedPaidContractsForCollector(collectorId);
        return new ResponseEntity<>(paidContracts, HttpStatus.OK);
    }

    @GetMapping("/{collectorId}/assigned-unpaid-contracts")
    public ResponseEntity<List<Contract>> getAssignedUnpaidContractsForCollector(@PathVariable Long collectorId) {
        List<Contract> unpaidContracts = collectionListService.getAssignedUnpaidContractsForCollector(collectorId);
        return new ResponseEntity<>(unpaidContracts, HttpStatus.OK);
    }
}