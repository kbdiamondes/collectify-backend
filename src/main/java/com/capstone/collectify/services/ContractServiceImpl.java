package com.capstone.collectify.services;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    @Autowired
    private CollectorRepository collectorRepository;

    @Override
    public Contract createContract(Contract contract) {
        // Implement the logic to create a new contract
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
    }

    @Override
    public List<Contract> getClientContracts(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));
        return contractRepository.findByClient(client);
    }

    @Override
    public List<Contract> getResellerContracts(Long resellerId) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));
        return contractRepository.findByReseller(reseller);
    }

    @Override
    public void assignCollectorToContract(Long contractId, Long collectorId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        contract.setCollector(collector);
        contractRepository.save(contract);
    }

    @Override
    public void collectPayment(Long contractId, BigDecimal amount) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        // Implement the logic to collect payment for the contract
        // Update contract properties as needed
        // contract.setDueAmount(...);
        // contract.setPaid(...);

        contractRepository.save(contract);
    }

    public Iterable<Contract> getContract() {
        return contractRepository.findAll();
    }

    private final String apiUrl = "https://tamworth-wallaby-raqd.2.sg-1.fl0.io/order/getAllOrders";

    public void fetchDataAndSaveToDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Contract[] contracts = restTemplate.getForObject(apiUrl, Contract[].class);

        if (contracts != null) {
            for (Contract contract : contracts) {

                String username = contract.getUsername();
                String itemName = contract.getItemName();
                BigDecimal dueAmount = contract.getDueAmount();
                Long fullPrice = contract.getFullPrice();

                // Map the JSON data to the Contract entity fields
                contract.setUsername(username);
                contract.setItemName(itemName); // Set the item name as needed
                contract.setDueAmount(dueAmount);
                contract.setFullPrice(fullPrice);
                contract.setPaid(false); // Initially set as unpaid

                // You can set other attributes and relationships as needed

                // Check if the contract already exists in the database using some unique identifier
                // If it doesn't exist, save it to the database
                if (!contractRepository.existsByUsername(contract.getUsername())) {
                    contractRepository.save(contract);
                }
            }
        }
    }

    // This method will run automatically every 5 minutes
    @Scheduled(fixedRate = 350000) // 5 minutes = 300,000 milliseconds
    public void scheduleFetchAndSave() {
        fetchDataAndSaveToDatabase();
    }


}

