package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;


public interface ClientService {

    Client createClient(Client client);

    List<Client> getClientsWithUnpaidContracts();

    Client getClientWithUnpaidContracts(Long clientId);

    Client getClientWithPaidContracts(Long clientId);


    void payDue(Long clientId, Long contractId, BigDecimal amount) throws AccessDeniedException;
    List<Contract> getClientContracts(Long clientId);

    Iterable<Client> getClient();

    ResponseEntity deleteClient(Long client_id);
    ResponseEntity updateClient(Long client_id, Client client);

    //    Optional - defines if the method may/may not return an object of the User Class
    Optional<Optional<Client>> findByUsername(String username);

    Optional<Client> getClientById(Long id);

    Contract createContractForClient(Long clientId, Contract contract);


}
