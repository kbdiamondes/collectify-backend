package com.capstone.collectify.controllers;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.services.ResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resellers")
public class ResellerController {

    @Autowired
    private ResellerService resellerService;

    @GetMapping
    public ResponseEntity<Object> getReseller() {
        return new ResponseEntity<>(resellerService.getReseller(), HttpStatus.OK);
    }

    @PostMapping
    public Reseller createReseller(@RequestBody Reseller reseller) {
        return resellerService.createReseller(reseller);
    }

    @GetMapping("/{id}")
    public Optional<Reseller> getResellerById(@PathVariable Long id) {
        return resellerService.getResellerById(id);
    }

    @GetMapping("/{id}/collection-history")
    public List<CollectionHistory> getCollectionHistory(@PathVariable Long id) {
        return resellerService.getCollectionHistory(id);
    }

    @PostMapping("/{resellerId}/clients/{clientId}/contracts")
    public Contract createContractForClientByReseller(@PathVariable Long resellerId, @PathVariable Long clientId, @RequestBody Contract contract) {
        return resellerService.createContract(resellerId, clientId, contract.getUsername(), contract.getItemName(), contract.getDueAmount(), contract.getFullPrice(), contract.isPaid());
    }

    @PostMapping("/{resellerId}/contracts/{contractId}/assign-collector")
    public void assignCollector(@PathVariable Long resellerId, @PathVariable Long contractId, @RequestBody Long collectorId) {
        try {
            resellerService.assignCollector(resellerId, contractId, collectorId);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{resellerId}/contracts/{contractId}/collect-payment")
    public void collectPayment(@PathVariable Long resellerId, @PathVariable Long contractId, @RequestBody BigDecimal amount) {
        try {
            resellerService.collectPayment(resellerId, contractId, amount);
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    // Add other endpoints for Reseller-related operations
}

