package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.PaymentAssuranceMessage;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.PaymentAssuranceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class PaymentAssuranceMessageController {
    @Autowired
    PaymentAssuranceMessageService paymentAssuranceMessageService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/paymentAssuranceMessage/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPaymentAssuranceMessage(@PathVariable Long collectorId, @RequestBody PaymentAssuranceMessage paymentAssuranceMessage) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            paymentAssuranceMessageService.createPaymentAssuranceMessage(collectorId, paymentAssuranceMessage);
            return new ResponseEntity<>("PaymentAssuranceMessage created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/paymentAssuranceMessage", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentAssuranceMessage() {
        return new ResponseEntity<>(paymentAssuranceMessageService.getPaymentAssuranceMessage(), HttpStatus.OK);
    }

    @RequestMapping(value="/paymentAssuranceMessage/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentAssuranceMessageByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(paymentAssuranceMessageService.getPaymentAssuranceMessageByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentAssuranceMessage/{collectorId}/{paymentAssuranceMessageId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePaymentAssuranceMessage(@PathVariable Long collectorId, @PathVariable Long paymentAssuranceMessageId) {
        return paymentAssuranceMessageService.deletePaymentAssuranceMessage(collectorId, paymentAssuranceMessageId);
    }

    @RequestMapping(value="/paymentAssuranceMessage/{collectorId}/{paymentAssuranceMessageId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePaymentAssuranceMessage(@PathVariable Long collectorId, @PathVariable Long paymentAssuranceMessageId, @RequestBody PaymentAssuranceMessage paymentAssuranceMessage) {
        return paymentAssuranceMessageService.updatePaymentAssuranceMessage(collectorId, paymentAssuranceMessageId, paymentAssuranceMessage);
    }
}