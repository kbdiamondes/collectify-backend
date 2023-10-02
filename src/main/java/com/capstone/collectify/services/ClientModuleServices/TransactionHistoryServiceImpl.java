package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.TransactionHistory;
import com.capstone.collectify.repositories.ClientModuleRepository.TransactionHistoryRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private ClientRepository clientRepository;
    public void createTransactionHistory(Long clientId,TransactionHistory transactionHistory){
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            transactionHistory.setClient(client);
            transactionHistoryRepository.save(transactionHistory);
        }
    }
    public Iterable<TransactionHistory> getTransactionHistory(){
        return transactionHistoryRepository.findAll();
    }

    public Iterable<TransactionHistory> getTransactionHistoryByClientId(Long clientId) {
        return transactionHistoryRepository.findTransactionHistoryByClientId(clientId);
    }
    
    public ResponseEntity deleteTransactionHistory(Long clientId,Long id){
        TransactionHistory transactionHistoryToDelete = transactionHistoryRepository.findById(id).orElse(null);
        if (transactionHistoryToDelete != null && transactionHistoryToDelete.getClient().getClient_id().equals(clientId)) {
            transactionHistoryRepository.deleteById(id);
            return new ResponseEntity<>("Transaction History Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Transaction History not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateTransactionHistory(Long clientId,Long id, TransactionHistory transactionHistory){
        TransactionHistory transactionHistoryForUpdate = transactionHistoryRepository.findById(id).orElse(null);
        if (transactionHistoryForUpdate != null && transactionHistoryForUpdate.getClient().getClient_id().equals(clientId)) {
            transactionHistoryForUpdate.setTransactionDate(transactionHistory.getTransactionDate());
            transactionHistoryForUpdate.setAmountSent(transactionHistory.getAmountSent());
            transactionHistoryRepository.save(transactionHistoryForUpdate);
            return new ResponseEntity<>("Transaction History Updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Transaction History not found",HttpStatus.NOT_FOUND);
    }
}
