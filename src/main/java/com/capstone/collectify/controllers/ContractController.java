package com.capstone.collectify.controllers;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.ContractService;
import org.hibernate.Hibernate;
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
        List<Contract> contracts = contractService.getClientContracts(clientId);

        // Load paymentTransactions eagerly for each contract
        contracts.forEach(contract -> {
            Hibernate.initialize(contract.getPaymentTransactions());
        });

        return contracts;
    }

    /*
    @GetMapping("/unpaid/{resellerId}")
    public ResponseEntity<List<Contract>> getUnpaidContractsForReseller(@PathVariable Long resellerId) {
        List<Contract> unpaidContracts = contractService.getUnpaidContractsForReseller(resellerId);
        return ResponseEntity.ok(unpaidContracts);
    }
*/
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

