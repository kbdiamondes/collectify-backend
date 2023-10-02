package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.PaymentMethodSelection;
import org.springframework.http.ResponseEntity;

public interface PaymentMethodSelectionService {
    void createPaymentMethodSelection(Long clientId,PaymentMethodSelection paymentMethodSelection);

    Iterable<PaymentMethodSelection> getPaymentMethodSelection();

    Iterable<PaymentMethodSelection>getPaymentMethodSelectionByClientId(Long clientId);

    ResponseEntity deletePaymentMethodSelection(Long clientId, Long id);

    ResponseEntity updatePaymentMethodSelection(Long clientId,Long id, PaymentMethodSelection paymentMethodSelection);
}
