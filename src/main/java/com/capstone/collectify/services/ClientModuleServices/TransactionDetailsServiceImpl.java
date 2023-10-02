package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.TransactionDetails;
import com.capstone.collectify.repositories.ClientModuleRepository.TransactionDetailsRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    public void createTransactionDetails(Long clientId,TransactionDetails transactionDetails){
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            transactionDetails.setClient(client);
            transactionDetailsRepository.save(transactionDetails);
        }
    }
    public Iterable<TransactionDetails> getTransactionDetails(){
        return transactionDetailsRepository.findAll();
    }

    public Iterable<TransactionDetails> getTransactionDetailsByClientId(Long clientId) {
        return transactionDetailsRepository.findTransactionDetailsByClientId(clientId);
    }
    public ResponseEntity deleteTransactionDetails(Long clientId,Long id){

        TransactionDetails transactionDetailsToDelete = transactionDetailsRepository.findById(id).orElse(null);

        if (transactionDetailsToDelete != null && transactionDetailsToDelete.getClient().getClient_id().equals(clientId)) {
            transactionDetailsRepository.deleteById(id);
            return new ResponseEntity<>("Transaction Details Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Transaction Details not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateTransactionDetails(Long clientId,Long id, TransactionDetails transactionDetails){
        TransactionDetails transactionDetailsForUpdate = transactionDetailsRepository.findById(id).orElse(null);
        if (transactionDetailsForUpdate != null && transactionDetailsForUpdate.getClient().getClient_id().equals(clientId)) {
            transactionDetailsForUpdate.setMonthlyCollectible(transactionDetails.getMonthlyCollectible());
            transactionDetailsForUpdate.setFullCashPrice(transactionDetails.getFullCashPrice());
            transactionDetailsForUpdate.setInterestRate(transactionDetails.getInterestRate());
            transactionDetailsForUpdate.setDueDate(transactionDetails.getDueDate());
            transactionDetailsForUpdate.setTotalPayment(transactionDetails.getTotalPayment());
            transactionDetailsRepository.save(transactionDetailsForUpdate);
            return new ResponseEntity("Transaction Details updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Transaction Details not found",HttpStatus.NOT_FOUND);
    }


}
