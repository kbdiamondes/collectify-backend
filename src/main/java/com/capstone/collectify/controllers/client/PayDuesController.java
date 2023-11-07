package com.capstone.collectify.controllers.client;

import com.capstone.collectify.controllers.filehandling.FileController;
import com.capstone.collectify.messages.ResponseMessage;
import com.capstone.collectify.models.Client;
import com.capstone.collectify.services.client.PayDuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Base64;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/paydues")
public class PayDuesController {

    @Autowired
    private PayDuesService payDuesService;

    @Autowired
    private FileController fileUploadController; // Inject your existing file upload controller

    /*
    @PostMapping("/client/{clientId}/contracts/{contractId}/pay")
    public ResponseEntity<String> payDues(
            @PathVariable Long clientId,
            @PathVariable Long contractId,
            @RequestParam Map<String,String> amount,
            @RequestParam("base64Image") String base64Image,
            @RequestParam("fileName") String fileName,
            @RequestParam("contentType") String contentType
    ) {
        try {
            if (base64Image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Base64 image data is empty.");
            }

            // Call your existing file upload controller to handle the file upload
            ResponseEntity<ResponseMessage> uploadResponse = fileUploadController.uploadFile(base64Image, fileName, contentType);

            if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                // Call the payDues service to handle the payment
                payDuesService.payDues(clientId, contractId, amount, base64Image, fileName, contentType);
                return ResponseEntity.ok("Payment successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + uploadResponse.getBody().getMessage());
            }
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
        }
    }

     */
}


/*
version alpha
    public ResponseEntity<String> payDues(
            @PathVariable Long clientId,
            @PathVariable Long contractId,
            @RequestParam Map<String,BigDecimal> amount,
            @RequestParam("base64Image") String base64Image,
            @RequestParam("fileName") String fileName,
            @RequestParam("contentType") String contentType
    ) {
        try {
            // Check if base64Image is present in the amount map
            if (!amount.containsKey("base64Image") || amount.get("base64Image") == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Base64 image data is empty.");
            }

            // Retrieve the base64Image from the amount map
            base64Image = String.valueOf(amount.get("base64Image"));

            // Call your existing file upload controller to handle the file upload
            ResponseEntity<ResponseMessage> uploadResponse = fileUploadController.uploadFile(base64Image, fileName, contentType);

            if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                // Call the payDues service to handle the payment
                payDuesService.payDues(clientId, contractId, amount, base64Image, fileName, contentType);
                return ResponseEntity.ok("Payment successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + uploadResponse.getBody().getMessage());
            }
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
        }
    }
 */
/*
    @PostMapping("/client/{clientId}/contracts/{contractId}/pay")
    public void payDue(@PathVariable Long clientId, @PathVariable Long contractId, @RequestBody Map<String, BigDecimal> requestBody) throws AccessDeniedException{
        BigDecimal amount = requestBody.get("amount");



        try{
            paymentService.payDues(clientId, contractId, amount);
        }catch(AccessDeniedException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }


    }
 */
