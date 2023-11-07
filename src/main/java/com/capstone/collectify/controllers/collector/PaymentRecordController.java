package com.capstone.collectify.controllers.collector;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.services.collector.PaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collector-payment-records")
public class PaymentRecordController {

    @Autowired
    private PaymentRecordService paymentRecordService;

    /*
    @GetMapping("/collector/{collectorId}")
    public ResponseEntity<List<Contract>> getPaymentRecordsForCollector(@PathVariable Long collectorId) {
        List<Contract> paymentRecords = paymentRecordService.getPaymentRecordsForCollector(collectorId);

        if (!paymentRecords.isEmpty()) {
            return new ResponseEntity<>(paymentRecords, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

}
