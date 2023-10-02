package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.PayDues;
import com.capstone.collectify.repositories.ClientModuleRepository.PayDuesRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PayDuesServiceImpl implements PayDuesService{
    @Autowired
    private PayDuesRepository payDuesRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    public void createPayDues(Long clientId,PayDues payDues){
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            payDues.setClient(client);
            payDuesRepository.save(payDues);
        }
    }
    public Iterable<PayDues> getPayDues(){
        return payDuesRepository.findAll();
    }
    
    public Iterable<PayDues> getPayDuesByClientId(Long clientId) {
        return payDuesRepository.findPayDuesByClientId(clientId);
    }
    public ResponseEntity deletePayDues(Long clientId,Long id){
        PayDues payDuesToDelete = payDuesRepository.findById(id).orElse(null);

        if (payDuesToDelete != null && payDuesToDelete.getClient().getClient_id().equals(clientId)) {
            payDuesRepository.deleteById(id);
            return new ResponseEntity<>("Pay Dues Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pay Dues not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updatePayDues(Long clientId,Long id, PayDues payDues){
        PayDues payDuesForUpdate = payDuesRepository.findById(id).orElse(null);
        if (payDuesForUpdate != null && payDuesForUpdate.getClient().getClient_id().equals(clientId)) {
            payDuesForUpdate.setItemPrice(payDues.getItemPrice());
            payDuesForUpdate.setReferenceNumber(payDues.getReferenceNumber());
            payDuesForUpdate.setPaymentType(payDues.getPaymentType());
            payDuesForUpdate.setTransactionProof(payDues.getTransactionProof());
            payDuesRepository.save(payDuesForUpdate);
            return new ResponseEntity<>("Pay Dues Updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Pay Dues not found",HttpStatus.NOT_FOUND);
    }
}
