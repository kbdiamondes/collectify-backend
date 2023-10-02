package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.ClientPaymentRecords;
import org.springframework.http.ResponseEntity;

public interface ClientPaymentRecordsService {
    void createClientPaymentRecords(Long clientId, ClientPaymentRecords clientPaymentRecords);

    Iterable<ClientPaymentRecords> getClientPaymentRecords();

    Iterable<ClientPaymentRecords>getClientPaymentRecordsByClientId(Long clientId);

    ResponseEntity deleteClientPaymentRecordsByClientId(Long clientId, Long id);

    /*ResponseEntity updateClientPaymentRecords(Long id, ClientPaymentRecords clientPaymentRecords);*/

}
