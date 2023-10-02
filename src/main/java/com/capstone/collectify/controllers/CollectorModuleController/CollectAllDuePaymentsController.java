package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.CollectAllDuePayments;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.CollectAllDuePaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class CollectAllDuePaymentsController {
    @Autowired
    CollectAllDuePaymentsService collectAllDuePaymentsService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/collectAllDuePayments/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createCollectAllDuePayments(@PathVariable Long collectorId, @RequestBody CollectAllDuePayments collectAllDuePayments) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            collectAllDuePaymentsService.createCollectAllDuePayments(collectorId, collectAllDuePayments);
            return new ResponseEntity<>("CollectAllDuePayments created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/collectAllDuePayments", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectAllDuePayments() {
        return new ResponseEntity<>(collectAllDuePaymentsService.getCollectAllDuePayments(), HttpStatus.OK);
    }

    @RequestMapping(value="/collectAllDuePayments/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectAllDuePaymentsByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(collectAllDuePaymentsService.getCollectAllDuePaymentsByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/collectAllDuePayments/{collectorId}/{collectAllDuePaymentsId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCollectAllDuePayments(@PathVariable Long collectorId, @PathVariable Long collectAllDuePaymentsId) {
        return collectAllDuePaymentsService.deleteCollectAllDuePayments(collectorId, collectAllDuePaymentsId);
    }

    @RequestMapping(value="/collectAllDuePayments/{collectorId}/{collectAllDuePaymentsId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollectAllDuePayments(@PathVariable Long collectorId, @PathVariable Long collectAllDuePaymentsId, @RequestBody CollectAllDuePayments collectAllDuePayments) {
        return collectAllDuePaymentsService.updateCollectAllDuePayments(collectorId, collectAllDuePaymentsId, collectAllDuePayments);
    }
}