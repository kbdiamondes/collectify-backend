package com.capstone.collectify.controllers;

import ch.qos.logback.classic.Logger;
import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.services.ResellerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/resellers")
public class ResellerController {

    @Autowired
    private ResellerService resellerService;

    @GetMapping
    public ResponseEntity<Object> getReseller() {
        return new ResponseEntity<>(resellerService.getReseller(), HttpStatus.OK);
    }

    @GetMapping("/{resellerId}/contracts/{contractId}/collector")
    public ResponseEntity<Collector> getAssignedCollector(@PathVariable Long resellerId, @PathVariable Long contractId) {
        try {
            Collector collector = resellerService.getAssignedCollector(resellerId, contractId);
            return new ResponseEntity<>(collector, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @PostMapping("/{resellerId}/clients/{clientUsername}/contracts")
    public Contract createContractForClientByReseller(@PathVariable Long resellerId, @PathVariable String clientUsername, @RequestBody Contract contract) {
        return resellerService.createContract(resellerId, clientUsername, contract);
    }


    @PostMapping("/{resellerId}/contracts/{contractId}/assign-collector")
    public void assignCollector(@PathVariable Long resellerId, @PathVariable Long contractId, @RequestBody Collector request) throws AccessDeniedException {
        Long collectorId = request.getCollector_id();
        resellerService.assignCollector(resellerId, contractId, collectorId);
    }

    /*
    @PostMapping("/{resellerId}/contracts/{contractId}/collect-payment")
    public ResponseEntity<String> collectPayment(@PathVariable Long resellerId, @PathVariable Long contractId, @RequestBody Map<String,BigDecimal> requestBody) {
        BigDecimal amount = requestBody.get("amount");

        try {
            resellerService.collectPayment(resellerId, contractId,  amount);
            return ResponseEntity.ok("Payment collected successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment collection failed: " + e.getMessage());
        }
    }
    */

    /*
    @GetMapping("/{resellerId}/active-unpaid-contracts/count")
    public ResponseEntity<Integer> countActiveUnpaidContractsForReseller(@PathVariable Long resellerId) {
        int count = resellerService.countActiveUnpaidContractsForReseller(resellerId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }*/

}

