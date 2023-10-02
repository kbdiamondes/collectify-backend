package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.PaymentMethodSelection;
import com.capstone.collectify.repositories.ClientModuleRepository.PaymentMethodSelectionRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodSelectionServiceImpl implements PaymentMethodSelectionService{
    @Autowired
    private PaymentMethodSelectionRepository paymentMethodSelectionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void createPaymentMethodSelection(Long clientId,PaymentMethodSelection paymentMethodSelection){
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null){
            paymentMethodSelection.setClient(client);
            paymentMethodSelectionRepository.save(paymentMethodSelection);
        }
    }
    public Iterable<PaymentMethodSelection> getPaymentMethodSelection(){
        return paymentMethodSelectionRepository.findAll();
    }

    public Iterable<PaymentMethodSelection>getPaymentMethodSelectionByClientId(Long clientId){
        return paymentMethodSelectionRepository.findPaymentMethodSelectionByClientId(clientId);
    }

    public ResponseEntity deletePaymentMethodSelection(Long clientId,Long id){
        PaymentMethodSelection paymentMethodSelectionToDelete = paymentMethodSelectionRepository.findById(id).orElse(null);
        if(paymentMethodSelectionToDelete != null && paymentMethodSelectionToDelete.getClient().getClient_id().equals(clientId)){
            paymentMethodSelectionRepository.deleteById(id);
            return new ResponseEntity<>("Payment Method Deleted Successfully!",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment Method not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updatePaymentMethodSelection(Long clientId, Long id, PaymentMethodSelection paymentMethodSelection){
        PaymentMethodSelection paymentMethodSelectionForUpdate = paymentMethodSelectionRepository.findById(id).orElse(null);
        if(paymentMethodSelectionForUpdate != null && paymentMethodSelectionForUpdate.getClient().getClient_id().equals(clientId)){
            paymentMethodSelectionForUpdate.setPurchaseMethod(paymentMethodSelection.getPurchaseMethod());
            paymentMethodSelectionForUpdate.setItemName(paymentMethodSelection.getItemName());
            paymentMethodSelectionForUpdate.setItemPrice(paymentMethodSelection.getItemPrice());
            paymentMethodSelectionForUpdate.setItemSpecs(paymentMethodSelection.getItemSpecs());
            paymentMethodSelectionForUpdate.setInstallmentAmount(paymentMethodSelection.getInstallmentAmount());
            paymentMethodSelectionRepository.save(paymentMethodSelectionForUpdate);
            return new ResponseEntity<>("Payment Method Updated!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Payment Method Not Found",HttpStatus.NOT_FOUND);
    }
}
