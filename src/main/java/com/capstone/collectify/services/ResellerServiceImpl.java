package com.capstone.collectify.services;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResellerServiceImpl implements ResellerService {

    @Autowired
    private ResellerRepository resellerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CollectorRepository collectorRepository;

    @Autowired
    private CollectionHistoryRepository collectionHistoryRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Reseller createReseller(Reseller reseller){
        String rawPassword = reseller.getPassword();

        // Perform password hashing using Spring Security's PasswordEncoder
        String encodedPassword = passwordEncoder.encode(rawPassword);


        reseller.setPassword(encodedPassword);

        resellerRepository.save(reseller);

        return reseller;
    }

    @Transactional
    public Contract createContract(Long resellerId, String clientUsername, Contract contract) {
        // Fetch the Reseller entity
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        // Fetch the Client entity
        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with username: " + clientUsername));


        LocalDate orderDate = LocalDate.now();

        // Set the Reseller and Client on the Contract
        contract.setReseller(reseller);
        contract.setClient(client);

        contract.setOrderdate(orderDate);
        contract.setOrderid(UUID.randomUUID().toString());

        // Save the contract
        Contract savedContract = contractRepository.save(contract);

        // Calculate starting date (order date + 15 days)
        LocalDate startingDate = contract.getOrderdate().plus(15, ChronoUnit.DAYS);


        contract.setDistributiondate(orderDate);
        // Check if installment duration is greater than 0
        if (contract.getInstallmentDuration() > 0) {
            double installmentAmount = contract.getFullPrice() / contract.getInstallmentDuration();

            // Create payment transactions
            for (int i = 1; i <= contract.getInstallmentDuration(); i++) {
                PaymentTransaction paymentTransaction = new PaymentTransaction();
                paymentTransaction.setContract(savedContract);
                paymentTransaction.setReseller(reseller);  // Set the Reseller on the PaymentTransaction
                paymentTransaction.setAmountdue(installmentAmount);
                paymentTransaction.setStartingdate(startingDate);
                paymentTransaction.setEnddate(startingDate.plus(15, ChronoUnit.DAYS));
                paymentTransaction.setInstallmentnumber(i);
                // Set other payment transaction attributes as needed
                paymentTransactionRepository.save(paymentTransaction);

                // Generate a random orderid
                paymentTransaction.setOrderid(UUID.randomUUID().toString());
                paymentTransaction.setPaymenttransactionid(UUID.randomUUID().toString());


                // Update starting date for the next installment
                startingDate = startingDate.plus(15, ChronoUnit.DAYS);
            }
        } else {
            // Installment duration is 0, create a single payment transaction
            PaymentTransaction paymentTransaction = new PaymentTransaction();
            paymentTransaction.setContract(savedContract);
            paymentTransaction.setReseller(reseller);  // Set the Reseller on the PaymentTransaction
            paymentTransaction.setAmountdue(contract.getFullPrice());
            paymentTransaction.setStartingdate(startingDate);
            paymentTransaction.setEnddate(startingDate.plus(15, ChronoUnit.DAYS));
            paymentTransaction.setInstallmentnumber(0); // Adjust as needed
            // Set other payment transaction attributes as needed
            paymentTransactionRepository.save(paymentTransaction);
        }

        return savedContract;
    }


    /*
    @Override
    public Contract createContract(Long resellerId, String clientUsername, String username, String itemName, Long fullPrice, Boolean isPaid, int installmentDuration, boolean isMonthly) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Client client = clientRepository.findByUsername(clientUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with username: " + username));

        Contract contract = new Contract();
        contract.setReseller(reseller);
        contract.setClient(client);
        contract.setUsername(clientUsername);
        contract.setItemName(itemName);
        contract.setFullPrice(fullPrice);
        contract.setInstallmentDuration(installmentDuration);
        contract.setIsMonthly(isMonthly);
        contract.setOrderdate(LocalDate.now());

        // Calculate and set the dueAmount based on isMonthly
        if (isMonthly) {
            // Calculate the monthly installment amount
            BigDecimal monthlyInstallmentAmount = BigDecimal.valueOf(fullPrice)
                    .divide(BigDecimal.valueOf(installmentDuration), 2, RoundingMode.HALF_UP); // Set scale and rounding mode

            // Set the calculated dueAmount
            contract.setDueAmount(monthlyInstallmentAmount);
        } else {
            // For non-monthly payments, set dueAmount equal to fullPrice
            contract.setDueAmount(BigDecimal.valueOf(fullPrice));
        }


        // Save the contract and return it
        return contractRepository.save(contract);
    }
*/
    @Override
    public void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getReseller().equals(reseller)) {
            Collector collector = collectorRepository.findById(collectorId)
                    .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

            contract.setCollector(collector);
            contractRepository.save(contract);
        } else {
            throw new AccessDeniedException("You don't have permission to assign a collector to this contract.");
        }
    }

    /*
    @Override
    public void collectPayment(Long resellerId, Long contractId, BigDecimal amount) throws AccessDeniedException {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getReseller().equals(reseller)) {
            // Check if the collected amount is less than or equal to the due amount
            if (amount.compareTo(contract.getDueAmount()) <= 0) {
                // Update the due amount and mark the contract as paid if necessary
                contract.setDueAmount(contract.getDueAmount().subtract(amount));
                if (contract.getDueAmount().compareTo(BigDecimal.ZERO) == 0) {
                   // contract.setPaid(true);
                }
                contractRepository.save(contract);

                // Record the collection history
                CollectionHistory history = new CollectionHistory();
                history.setCollectedAmount(amount);
                history.setCollectionDate(LocalDateTime.now());
                history.setReseller(reseller);
                collectionHistoryRepository.save(history);

                System.out.println(amount + "is successfully collected");
            } else {
                throw new IllegalArgumentException("Collected amount exceeds the due amount.");
            }
        } else {
            throw new AccessDeniedException("You don't have permission to collect payment for this contract.");
        }
    }
*/
    @Override
    public List<CollectionHistory> getCollectionHistory(Long resellerId) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        return collectionHistoryRepository.findByReseller(reseller);
    }


    @Override
    public Optional<Reseller> getResellerById(Long id) {
        return resellerRepository.findById(id);
    }

    @Override
    public Iterable<Reseller> getReseller() {
        return resellerRepository.findAll();
    }

    @Override
    public Collector getAssignedCollector(Long resellerId, Long contractId) {
        // Step 1: Retrieve the Reseller
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        // Step 2: Retrieve the Contract
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        // Step 3: Get the Assigned Collector
        Collector assignedCollector = contract.getCollector();

        // Step 4: Return the Assigned Collector
        return assignedCollector;
    }

    /*
    @Override
    public int countActiveUnpaidContractsForReseller(Long resellerId) {
        return contractRepository.countActiveUnpaidContractsForReseller(resellerId);
    }*/

    @Value("${api.endpoint.getDistributors}")
    private String apiUrl;

    public void fetchDataAndSaveToDatabase() {
        RestTemplate restTemplate = new RestTemplate();
        Reseller[] resellers = restTemplate.getForObject(apiUrl, Reseller[].class);

        if (resellers!= null) {
            for (Reseller reseller : resellers) {
                // Extract first, middle, and last names from JSON response
                String firstname = reseller.getFirstname();
                String middlename = reseller.getMiddlename();
                String lastname = reseller.getLastname();
                String address = reseller.getAddress();


                // Concatenate first, middle, and last names into the fullName field
                String userName = firstname+"."+lastname;
                String password = lastname+"123";
                String fullName = firstname + " " + middlename + " " + lastname;
                String email = firstname + lastname + "@gmail.com";
                String collectorAddress = address;

                reseller.setUsername(userName);
                reseller.setFullName(fullName);
                reseller.setEmail(email);
                reseller.setPassword(password);
                reseller.setAddress(collectorAddress);

                // Check if the collector already exists in the database using some unique identifier (e.g., username or email)
                // If it doesn't exist, save it to the database
                if (!resellerRepository.existsByUsername(reseller.getUsername())
                        && !resellerRepository.existsByEmail(reseller.getEmail())) {
                    resellerRepository.save(reseller);
                }
            }
        }
    }

    // This method will run automatically every 5 minutes
    @Scheduled(fixedRate = 5000) // 5 minutes = 300,000 milliseconds
    public void scheduleFetchAndSave() {
        fetchDataAndSaveToDatabase();
    }


}

/*
v2
@Override
    public Contract createContract(Long resellerId,  Long clientId, String username, String itemName, BigDecimal dueAmount, Long fullPrice, Boolean isPaid) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + username));

        Contract contract = new Contract();
        contract.setReseller(reseller);
        contract.setClient(client);
        contract.setUsername(username);
        contract.setItemName(itemName);
        contract.setDueAmount(dueAmount);
        contract.setFullPrice(fullPrice);
        contract.setPaid(isPaid);

        // Save the contract and return it
        return contractRepository.save(contract);
    }
 */