package com.capstone.collectify.services.CollectorModuleServices;

import com.capstone.collectify.models.CollectorModules.PaymentAssuranceMessage;
import org.springframework.http.ResponseEntity;

public interface PaymentAssuranceMessageService {
    void createPaymentAssuranceMessage(Long collectorId, PaymentAssuranceMessage paymentDues);

    Iterable<PaymentAssuranceMessage> getPaymentAssuranceMessage();

    Iterable<PaymentAssuranceMessage> getPaymentAssuranceMessageByCollectorId(Long collectorId);

    ResponseEntity deletePaymentAssuranceMessage(Long collectorId, Long id);

    ResponseEntity updatePaymentAssuranceMessage(Long collectorId, Long id, PaymentAssuranceMessage paymentDues);
}
