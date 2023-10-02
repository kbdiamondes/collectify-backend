package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.PayDues;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.PayDuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class PayDuesController {
    @Autowired
    PayDuesService payDuesService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/payDues/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPayDues(@PathVariable Long clientId,@RequestBody PayDues payDues) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            payDuesService.createPayDues(clientId,payDues);
            return new ResponseEntity<>("Pay Dues created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Pay Dues Payment Records does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/payDues", method = RequestMethod.GET)
    public ResponseEntity<Object> getPayDues() {
        return new ResponseEntity<>(payDuesService.getPayDues(), HttpStatus.OK);
    }

    @RequestMapping(value="/payDues/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPayDuesByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(payDuesService.getPayDuesByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/payDues/{clientId}/{payDues_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePayDues(@PathVariable Long clientId,@PathVariable Long payDues_id) {
        return payDuesService.deletePayDues(clientId,payDues_id);
    }

    @RequestMapping(value="/payDues/{clientId}/{payDuesid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePayDues(@PathVariable Long clientId,@PathVariable Long payDuesid, @RequestBody PayDues payDues) {
        return payDuesService.updatePayDues(clientId,payDuesid, payDues);
    }
}