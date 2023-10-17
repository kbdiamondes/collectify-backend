package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @GetMapping("/unpaid")
    public List<Contract> getAllUnpaidContracts() {
        List<Contract> unpaidContracts = contractService.getAllUnpaidContracts();
        return unpaidContracts;
    }

    @GetMapping
    public ResponseEntity<Object> getContracts() {
        return new ResponseEntity<>(contractService.getContract(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Contract> getClientContracts(@PathVariable Long clientId) {
        return contractService.getClientContracts(clientId);
    }

    @GetMapping("/reseller/{resellerId}")
    public List<Contract> getResellerContracts(@PathVariable Long resellerId) {
        return contractService.getResellerContracts(resellerId);
    }

    @PostMapping("/{contractId}/assign-collector/{collectorId}")
    public void assignCollectorToContract(@PathVariable Long contractId, @PathVariable Long collectorId) {
        contractService.assignCollectorToContract(contractId, collectorId);
    }

    @PostMapping("/{contractId}/collect-payment")
    public void collectPayment(@PathVariable Long contractId, @RequestBody BigDecimal amount) {
        contractService.collectPayment(contractId, amount);
    }

    // Add other endpoints for Contract-related operations
}

