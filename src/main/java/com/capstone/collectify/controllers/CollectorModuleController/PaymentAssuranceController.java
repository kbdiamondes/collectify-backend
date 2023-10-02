package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.PaymentAssurance;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.PaymentAssuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class PaymentAssuranceController {
    @Autowired
    PaymentAssuranceService paymentAssuranceService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/paymentAssurance/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPaymentAssurance(@PathVariable Long collectorId, @RequestBody PaymentAssurance paymentAssurance) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            paymentAssuranceService.createPaymentAssurance(collectorId, paymentAssurance);
            return new ResponseEntity<>("PaymentAssurance created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/paymentAssurance", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentAssurance() {
        return new ResponseEntity<>(paymentAssuranceService.getPaymentAssurance(), HttpStatus.OK);
    }

    @RequestMapping(value="/paymentAssurance/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPaymentAssuranceByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(paymentAssuranceService.getPaymentAssuranceByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/paymentAssurance/{collectorId}/{paymentAssuranceId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePaymentAssurance(@PathVariable Long collectorId, @PathVariable Long paymentAssuranceId) {
        return paymentAssuranceService.deletePaymentAssurance(collectorId, paymentAssuranceId);
    }

    @RequestMapping(value="/paymentAssurance/{collectorId}/{paymentAssuranceId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePaymentAssurance(@PathVariable Long collectorId, @PathVariable Long paymentAssuranceId, @RequestBody PaymentAssurance paymentAssurance) {
        return paymentAssuranceService.updatePaymentAssurance(collectorId, paymentAssuranceId, paymentAssurance);
    }
}