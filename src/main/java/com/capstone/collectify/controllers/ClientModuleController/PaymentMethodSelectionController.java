package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.PaymentMethodSelection;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.PaymentMethodSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class PaymentMethodSelectionController {
    @Autowired
    PaymentMethodSelectionService paymentMethodSelectionService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/paymentMethodSelection/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPaymentMethodSelection(@PathVariable Long clientId,@RequestBody PaymentMethodSelection paymentMethodSelection) {

        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            paymentMethodSelectionService.createPaymentMethodSelection(clientId,paymentMethodSelection);
            return new ResponseEntity<>("Payment Method Selection created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Payment Method Selection does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/paymentMethodSelection", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentMethodSelection() {
        return new ResponseEntity<>(paymentMethodSelectionService.getPaymentMethodSelection(), HttpStatus.OK);
    }
    @RequestMapping(value="/paymentMethodSelection/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentMethodSelectionByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(paymentMethodSelectionService.getPaymentMethodSelectionByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentMethodSelection/{clientId}/{paymentMethodSelection_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePaymentMethodSelection(@PathVariable Long clientId,@PathVariable Long paymentMethodSelection_id) {
        return paymentMethodSelectionService.deletePaymentMethodSelection(clientId,paymentMethodSelection_id);
    }

    @RequestMapping(value="/paymentMethodSelection/{clientId}/{paymentMethodSelection_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePaymentMethodSelection(@PathVariable Long clientId, @PathVariable Long paymentMethodSelection_id, @RequestBody PaymentMethodSelection paymentMethodSelection) {
        return paymentMethodSelectionService.updatePaymentMethodSelection(clientId,paymentMethodSelection_id, paymentMethodSelection);
    }
}