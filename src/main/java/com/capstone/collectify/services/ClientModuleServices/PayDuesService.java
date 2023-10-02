package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.PayDues;
import org.springframework.http.ResponseEntity;

public interface PayDuesService {
    void createPayDues(Long clienId,PayDues payDues);

    Iterable<PayDues> getPayDues();

    Iterable<PayDues>getPayDuesByClientId(Long clientId);

    ResponseEntity deletePayDues(Long clientId, Long id);

    ResponseEntity updatePayDues(Long clientId,Long id, PayDues payDues);
}
