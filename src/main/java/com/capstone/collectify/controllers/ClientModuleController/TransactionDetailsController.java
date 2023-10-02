package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.TransactionDetails;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class TransactionDetailsController {
    @Autowired
    TransactionDetailsService transactionDetailsService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/transactionDetails/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransactionDetails(@PathVariable Long clientId,@RequestBody TransactionDetails transactionDetails) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            transactionDetailsService.createTransactionDetails(clientId, transactionDetails);
            return new ResponseEntity<>("Transaction Details created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Transaction Details does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/transactionDetails", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionDetails() {
        return new ResponseEntity<>(transactionDetailsService.getTransactionDetails(), HttpStatus.OK);
    }

    @RequestMapping(value="/transactionDetails/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getTransactionDetailsByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(transactionDetailsService.getTransactionDetailsByClientId(clientId), HttpStatus.OK);
    }
    @RequestMapping(value = "/transactionDetails/{clientId}/{transactionDetails_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransactionDetails(@PathVariable Long clientId,@PathVariable Long transactionDetails_id) {
        return transactionDetailsService.deleteTransactionDetails(clientId,transactionDetails_id);
    }

    @RequestMapping(value="/transactionDetails/{clientId}/{transactionDetails_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTransactionDetails(@PathVariable Long clientId, @PathVariable Long transactionDetails_id, @RequestBody TransactionDetails transactionDetails) {
        return transactionDetailsService.updateTransactionDetails(clientId,transactionDetails_id, transactionDetails);
    }
}