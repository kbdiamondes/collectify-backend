package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.ClientPaymentRecords;
import com.capstone.collectify.repositories.ClientModuleRepository.ClientPaymentRecordsRepository;
import com.capstone.collectify.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientPaymentRecordsServiceImpl implements ClientPaymentRecordsService {
    @Autowired
    private ClientPaymentRecordsRepository clientPaymentRecordsRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void createClientPaymentRecords(Long clientId, ClientPaymentRecords clientPaymentRecords){
        Client client = clientRepository.findById(clientId).orElse(null);
        if(client != null){
            clientPaymentRecords.setClient(client);
            clientPaymentRecordsRepository.save(clientPaymentRecords);
        }
    }
    public Iterable<ClientPaymentRecords> getClientPaymentRecords(){
        return clientPaymentRecordsRepository.findAll();
    }
    public Iterable<ClientPaymentRecords>getClientPaymentRecordsByClientId(Long clientId){
        return clientPaymentRecordsRepository.findClientPaymentRecordsClientId(clientId);
    }
    public ResponseEntity deleteClientPaymentRecordsByClientId(Long clientId,Long id){
        ClientPaymentRecords clientPaymentRecordsToDelete = clientPaymentRecordsRepository.findById(id).orElse(null);

        if(clientPaymentRecordsToDelete != null && clientPaymentRecordsToDelete.getClient().getClient_id().equals(clientId)){
            clientPaymentRecordsRepository.deleteById(id);
            return new ResponseEntity<>("Client Payment Records Deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Due payment not found or does not belong to the specified client", HttpStatus.NOT_FOUND);
        }
    }

//    public ResponseEntity updateClientPaymentRecords(Long id, ClientPaymentRecords clientPaymentRecords){
//        ClientPaymentRecords clientPaymentRecordsForUpdate = clientPaymentRecordsRepository.findById(id).get();
//        clientPaymentRecordsForUpdate.setClient(clientPaymentRecords.getClient());
//        clientPaymentRecordsForUpdate.setPayDues(clientPaymentRecords.getPayDues());
//        clientPaymentRecordsForUpdate.setCollectPayments(clientPaymentRecords.getCollectPayments());
//        clientPaymentRecordsRepository.save(clientPaymentRecordsForUpdate);
//        return new ResponseEntity("ClientPaymentRecords updated successfully", HttpStatus.OK);
//    }
}
