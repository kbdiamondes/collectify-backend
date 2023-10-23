package com.capstone.collectify.controllers.client;
import com.capstone.collectify.models.TransactionHistory;
import com.capstone.collectify.services.client.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-history")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/client/{clientId}")
    public List<TransactionHistory> getAllTransactionsByClient(@PathVariable Long clientId) {
        return transactionHistoryService.getAllTransactionsByClient(clientId);
    }
}
