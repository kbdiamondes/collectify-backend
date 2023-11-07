package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.PaymentTransaction;

import java.util.List;

public interface DuePaymentsService {
    List<PaymentTransaction> getUnpaidPaymentTransactionsByResellerId(Long resellerId);

    List<PaymentTransaction> getUnpaidPaymentTransactionsByClientId(Long clientId);

    //List<Client> getClientsWithUnpaidContracts();

    //Client getClientWithUnpaidContracts(Long clientId);

    //Client getClientWithPaidContracts(Long clientId);

}
