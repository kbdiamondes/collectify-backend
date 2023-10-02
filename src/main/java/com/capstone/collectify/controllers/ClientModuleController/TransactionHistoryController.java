package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.TransactionHistory;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class TransactionHistoryController {
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/transactionHistory/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransactionHistory(@PathVariable Long clientId,@RequestBody TransactionHistory transactionHistory) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            transactionHistoryService.createTransactionHistory(clientId,transactionHistory);
            return new ResponseEntity<>("Transaction History created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Transaction History Records does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value="/transactionHistory", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionHistory() {
        return new ResponseEntity<>(transactionHistoryService.getTransactionHistory(), HttpStatus.OK);
    }
    @RequestMapping(value="/transactionHistory/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionHistoryByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(transactionHistoryService.getTransactionHistoryByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/transactionHistory/{clientId}/{transactionHistory_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransactionHistory(@PathVariable Long clientId,@PathVariable Long transactionHistory_id) {
        return transactionHistoryService.deleteTransactionHistory(clientId,transactionHistory_id);
    }

    @RequestMapping(value="/transactionHistory/{clientId}/{transactionHistory_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTransactionHistory(@PathVariable Long clientId,@PathVariable Long transactionHistory_id, @RequestBody TransactionHistory transactionHistory) {
        return transactionHistoryService.updateTransactionHistory(clientId,transactionHistory_id, transactionHistory);
    }
}