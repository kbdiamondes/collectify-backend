package com.capstone.collectify.controllers.others;

import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionWithClientAndItemDTO;
import com.capstone.collectify.services.others.PaymentTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-transactions")
@CrossOrigin
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @GetMapping
    public Iterable<PaymentTransaction> getAllPaymentTransactions() {
        return paymentTransactionService.getPaymentTransaction();
    }

    @GetMapping("/client/{clientId}")
    public List<PaymentTransaction> getPaymentTransactionsByClientId(@PathVariable Long clientId) {
        return paymentTransactionService.getPaymentTransactionsByClientId(clientId);
    }

    @GetMapping("/reseller/{resellerId}")
    public ResponseEntity<List<PaymentTransactionWithClientAndItemDTO>> getPaymentTransactionsByResellerId(@PathVariable Long resellerId) {
        List<PaymentTransactionWithClientAndItemDTO> paymentTransactions = paymentTransactionService.getPaymentTransactionsWithClientAndItemByResellerId(resellerId);

        if (!paymentTransactions.isEmpty()) {
            return new ResponseEntity<>(paymentTransactions, HttpStatus.OK);
        } else if(paymentTransactions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/reseller/unpaid/{resellerId}")
    public ResponseEntity<List<PaymentTransactionWithClientAndItemDTO>> getUnpaidPaymentTransactionsByResellerId(@PathVariable Long resellerId) {
        List<PaymentTransactionWithClientAndItemDTO> paymentTransactions = paymentTransactionService.getUnpaidPaymentTransactionsWithClientAndItemByResellerId(resellerId);

        if (!paymentTransactions.isEmpty()) {
            return new ResponseEntity<>(paymentTransactions, HttpStatus.OK);
        } else if(paymentTransactions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reseller/uncollected/{resellerId}")
    public ResponseEntity<List<PaymentTransactionWithClientAndItemDTO>> getUncollectedPaymentTransactionsByResellerId(@PathVariable Long resellerId) {
        List<PaymentTransactionWithClientAndItemDTO> paymentTransactions = paymentTransactionService.getUncollectedPaymentTransactionsWithClientAndItemByResellerId(resellerId);

        if (!paymentTransactions.isEmpty()) {
            return new ResponseEntity<>(paymentTransactions, HttpStatus.OK);
        } else if(paymentTransactions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reseller/uncollected-unassigned/{resellerId}")
    public ResponseEntity<List<PaymentTransactionWithClientAndItemDTO>> getUncollectedAndUnassignedPaymentTransactionsByResellerId(@PathVariable Long resellerId) {
        List<PaymentTransactionWithClientAndItemDTO> paymentTransactions = paymentTransactionService.getUncollectedAndUnassignedPaymentTransactionsWithClientAndItemByResellerId(resellerId);

        if (!paymentTransactions.isEmpty()) {
            return new ResponseEntity<>(paymentTransactions, HttpStatus.OK);
        } else if(paymentTransactions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
