package com.capstone.collectify.controllers.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.services.client.PayDuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Map;

@RestController
@RequestMapping("/paydues")
public class PayDuesController {

    private final PayDuesService paymentService;

    @Autowired
    public PayDuesController(PayDuesService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/client/{clientId}/contracts/{contractId}/pay")
    public void payDue(@PathVariable Long clientId, @PathVariable Long contractId, @RequestBody Map<String, BigDecimal> requestBody) throws AccessDeniedException {
        BigDecimal amount = requestBody.get("amount");
        try{
            paymentService.payDues(clientId, contractId, amount);
        }catch(AccessDeniedException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }


    }
}

