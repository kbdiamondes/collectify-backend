package com.capstone.collectify.controllers.collector;

import com.capstone.collectify.controllers.filehandling.FileController;
import com.capstone.collectify.messages.ResponseMessage;
import com.capstone.collectify.services.collector.CollectorCollectPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@CrossOrigin
@RequestMapping("/collector")
public class CollectorCollectPaymentsController {

    @Autowired
    private final CollectorCollectPaymentsService paymentCollectionService;

    @Autowired
    private FileController fileUploadController; // Inject your existing file upload controller

    @Autowired
    public CollectorCollectPaymentsController(CollectorCollectPaymentsService paymentCollectionService) {
        this.paymentCollectionService = paymentCollectionService;
    }
/*
    @PostMapping("/collectPayment/{collectorId}/contracts/{contractId}/collect-payment")
    public ResponseEntity<String> collectPayment(
            @PathVariable Long collectorId,
            @PathVariable Long contractId,
            @RequestParam String paymentType,
            @RequestParam("base64Image") String base64Image,
            @RequestParam("fileName") String fileName,
            @RequestParam("contentType") String contentType) {
        try {
            if (base64Image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Base64 image data is empty.");
            }

            // Call your existing file upload controller to handle the file upload
            ResponseEntity<ResponseMessage> uploadResponse = fileUploadController.uploadFile(base64Image, fileName, contentType);

            if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                paymentCollectionService.collectPayments(collectorId, contractId, paymentType, base64Image, fileName, contentType);
                return ResponseEntity.ok("Payment collected successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + uploadResponse.getBody().getMessage());
            }
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment collection failed: " + e.getMessage());
        }
    }

    @PostMapping("/collectPayment/{collectorId}/collect-all-payments")
    public ResponseEntity<String> collectAllPayments(
            @PathVariable Long collectorId,
            @RequestParam String paymentType,
            @RequestParam("base64Image") String base64Image,
            @RequestParam("fileName") String fileName,
            @RequestParam("contentType") String contentType) {
        try {
            if (base64Image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Base64 image data is empty.");
            }

            // Call your existing file upload controller to handle the file upload
            ResponseEntity<ResponseMessage> uploadResponse = fileUploadController.uploadFile(base64Image, fileName, contentType);

            if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                paymentCollectionService.collectPaymentsFromAllContracts(collectorId, paymentType, base64Image, fileName, contentType);
                return ResponseEntity.ok("Payment collected successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + uploadResponse.getBody().getMessage());
            }
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment collection failed: " + e.getMessage());
        }
    }
*/
}

