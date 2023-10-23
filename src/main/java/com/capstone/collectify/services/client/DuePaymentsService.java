package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;

import java.util.List;

public interface DuePaymentsService {

    List<Client> getClientsWithUnpaidContracts();

    Client getClientWithUnpaidContracts(Long clientId);

    Client getClientWithPaidContracts(Long clientId);

}
