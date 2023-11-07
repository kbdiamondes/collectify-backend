package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.PaymentTransactionRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DuePaymentsServiceImpl implements DuePaymentsService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ResellerRepository resellerRepository;

    // Method to get unpaid payment transactions by Client ID
    @Override
    public List<PaymentTransaction> getUnpaidPaymentTransactionsByClientId(Long clientId) {
        return paymentTransactionRepository.findUnpaidPaymentTransactionsByClientId(clientId);
    }

    // Method to get unpaid payment transactions by Reseller ID
    @Override
    public List<PaymentTransaction> getUnpaidPaymentTransactionsByResellerId(Long resellerId) {
        return paymentTransactionRepository.findUnpaidPaymentTransactionsByResellerId(resellerId);
    }

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
