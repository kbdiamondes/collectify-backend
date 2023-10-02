package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.ClientPaymentRecords;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.ClientPaymentRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class ClientPaymentRecordsController {
    @Autowired
    ClientPaymentRecordsService clientPaymentRecordsService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/clientPaymentRecords/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createClientPaymentRecords(@PathVariable Long clientId, @RequestBody ClientPaymentRecords clientPaymentRecords) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            clientPaymentRecordsService.createClientPaymentRecords(clientId,clientPaymentRecords);
            return new ResponseEntity<>("Client Payment Records created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Client Payment Records does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/clientPaymentRecords/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getClientPaymentRecordsByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(clientPaymentRecordsService.getClientPaymentRecordsByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/clientPaymentRecords/{clientId}/{clientPaymentRecords_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClientPaymentRecordsByClientId(@PathVariable Long clientId, @PathVariable Long clientPaymentRecords_id ) {
        return clientPaymentRecordsService.deleteClientPaymentRecordsByClientId(clientId,clientPaymentRecords_id);
    }

    /*@RequestMapping(value="/clientPaymentRecords/{clientPaymentRecordsid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClientPaymentRecords(@PathVariable Long clientPaymentRecordsid, @RequestBody ClientPaymentRecords clientPaymentRecords) {
        return clientPaymentRecordsService.updateClientPaymentRecords(clientPaymentRecordsid, clientPaymentRecords);
    }*/
}