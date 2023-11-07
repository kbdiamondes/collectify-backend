package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DuePaymentsServiceImpl implements DuePaymentsService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ContractRepository contractRepository;

    /*
    @Override
    public List<Client> getClientsWithUnpaidContracts() {
        // Use the ClientRepository to retrieve clients with contracts where isPaid is false
        return clientRepository.findClientsWithUnpaidContracts();
    }*/

    /*
    @Override
    public Client getClientWithUnpaidContracts(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        // Add the logic to retrieve the client's unpaid contracts
        List<Contract> unpaidContracts = contractRepository.findByClientAndIsPaid(client, false);

        // Set the client's contracts to the unpaid contracts
        client.setContracts(unpaidContracts);

        return client;
    }

    @Override
    public Client getClientWithPaidContracts(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        // Add the logic to retrieve the client's paid contracts
        List<Contract> paidContracts = contractRepository.findByClientAndIsPaid(client, true);

        // Set the client's contracts to the paid contracts
        client.setContracts(paidContracts);

        return client;
    }

     */
}
