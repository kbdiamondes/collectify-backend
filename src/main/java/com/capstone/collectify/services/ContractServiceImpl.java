package com.capstone.collectify.services;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.*;
import com.capstone.collectify.services.filehandling.FileStorageService;
import org.apache.velocity.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    @Autowired
    private CollectorRepository collectorRepository;

    private final FileStorageService fileStorageService;

    @Autowired
    private CollectionHistoryRepository collectionHistoryRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    private String base64ImageData;
    private String fileName;
    private String contentType;

    public ContractServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    /*
    @Override
    public List<Contract> getUnpaidContractsForReseller(Long resellerId) {
        return contractRepository.findUnpaidContractsForReseller(resellerId);
    }
*/
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

    /*
    @Scheduled(cron = "0 0 0 1 * ?") // Run at midnight on the 1st day of each month
    public void processMonthlyPayments() throws IOException {

        // Get the current date and time
        LocalDateTime currentDate = LocalDateTime.now();

        // Iterate through contracts with isMonthly=true and calculate monthly payments
        List<Contract> monthlyPaymentContracts = contractRepository.findByIsMonthly(true);

        for (Contract contract : monthlyPaymentContracts) {
            if (!contract.isPaid()) {
                // Calculate the next payment date based on the contract's payment plan
                LocalDateTime lastPaymentDate = contract.getLastPaymentDate();
                int paymentPlanMonths = contract.getInstallmentDuration();
                LocalDateTime nextPaymentDate = lastPaymentDate.plusMonths(paymentPlanMonths);
                if (currentDate.toLocalDate().isEqual(nextPaymentDate.toLocalDate())) {
                    // Calculate the monthly payment amount
                    BigDecimal monthlyInstallmentAmount = contract.calculateMonthlyInstallmentAmount(contract.isIsMonthly());

                    // Check if the client has sufficient balance
                    if (contract.getDueAmount().compareTo(monthlyInstallmentAmount) >= 0) {
                        // Update the due amount and mark the contract as paid if necessary
                        contract.setDueAmount(contract.getDueAmount().subtract(monthlyInstallmentAmount));
                        if (contract.getDueAmount().compareTo(BigDecimal.ZERO) == 0) {
                            contract.setPaid(true);
                        }

                        // Record the collection history (you can customize this part)
                        CollectionHistory history = new CollectionHistory();
                        history.setCollectedAmount(monthlyInstallmentAmount);
                        history.setCollectionDate(currentDate);
                        history.setClient(contract.getClient());
                        // You can also set other relevant attributes in the history

                        // Store the image data and associate it with the contract
                        FileDB fileDB = fileStorageService.store(base64ImageData, fileName, contentType);
                        history.setTransactionProof(fileDB);

                        // Set the lastPaymentDate to nextPaymentDate
                        contract.setLastPaymentDate(nextPaymentDate);

                        // Save the contract and collection history
                        contractRepository.save(contract);
                        collectionHistoryRepository.save(history);

                        System.out.println("Payment collected: " + monthlyInstallmentAmount + " for contract: " + contract.getContract_id());
                    } else {
                        // Handle insufficient balance (e.g., send notifications)
                        // You can implement this part based on your business logic
                    }
                }

            }
        }
    }
*/

    @Value("${api.endpoint.getOrders}")
    private String apiUrl;

    @PersistenceContext
    private EntityManager entityManager;

        public void fetchDataAndSaveToDatabase() {
            RestTemplate restTemplate = new RestTemplate();
            Contract[] contracts = restTemplate.getForObject(apiUrl, Contract[].class);



            if (contracts != null) {
                for (Contract externalContract : contracts) {
                    String externalOrderId = externalContract.getOrderid();

                    // Check if the contract already exists in the database using the external order ID
                    if (!contractRepository.existsByOrderid(externalOrderId)) {
                        // Create a new Contract entity
                        Contract contract = new Contract();

                        // Check if the distributor (reseller) information exists in the external data
                        Reseller externalDistributor = externalContract.getReseller();
                        Reseller reseller = null;
                        if (externalDistributor != null) {
                            // Check if the reseller with the same username already exists
                            Optional<Reseller> existingReseller = resellerRepository.findByUsername(externalDistributor.getUsername());

                            reseller = existingReseller.orElseGet(() -> {
                                Reseller newReseller = new Reseller();
                                // Map and set distributor attributes
                                newReseller.setUsername(externalDistributor.getUsername());
                                newReseller.setFullName(externalDistributor.getFirstname() + " " + externalDistributor.getMiddlename() + " " + externalDistributor.getLastname());
                                newReseller.setFirstname(externalDistributor.getFirstname());
                                newReseller.setMiddlename(externalDistributor.getMiddlename());
                                newReseller.setLastname(externalDistributor.getLastname());
                                newReseller.setEmail(externalDistributor.getEmail());
                                newReseller.setAddress(externalDistributor.getAddress());
                                newReseller.setPassword(externalDistributor.getPassword());
                                return resellerRepository.save(newReseller);
                            });

                            contract.setReseller(reseller);
                            System.out.println("External Reseller: " + reseller);
                        } else {
                            // External distributor is null, set the field to null
                            contract.setReseller(null);
                            System.out.println("External Reseller is null.");
                        }

                        // Check if the dealer (client) information exists in the external data
                        Client externalDealer = externalContract.getClient();
                        Client client = null;
                        if (externalDealer != null) {
                            // Check if the client with the same username already exists
                            Optional<Client> existingClient = clientRepository.findByUsername(externalDealer.getUsername());

                            client = existingClient.orElseGet(() -> {
                                Client newClient = new Client();
                                // Map and set dealer attributes
                                newClient.setUsername(externalDealer.getUsername());

                                String username = externalDealer.getFirstname() + "." + externalDealer.getLastname();

                                newClient.setFullName(externalDealer.getFirstname() + " " + externalDealer.getMiddlename() + " " + externalDealer.getLastname());
                                newClient.setUsername(username);
                                newClient.setFirstname(externalDealer.getFirstname());
                                newClient.setMiddlename(externalDealer.getMiddlename());
                                newClient.setLastname(externalDealer.getLastname());
                                newClient.setEmail(externalDealer.getEmail());
                                newClient.setAddress(externalDealer.getAddress());
                                newClient.setPassword(externalDealer.getPassword());

                                // Set Contract Client's username
                                contract.setUsername(username);
                                return clientRepository.save(newClient);
                            });

                            contract.setClient(client);
                            System.out.println("External Client: " + client);
                        } else {
                            // External client is null, set the field to null
                            contract.setClient(null);
                            System.out.println("External Client is null.");
                        }

                        // Map fields from the external API data to your Contract entity
                        contract.setOrderid(externalOrderId);
                        contract.setOrderdate(externalContract.getOrderdate());
                        contract.setDistributiondate(externalContract.getDistributiondate());
                        contract.setPenaltyrate(externalContract.getPenaltyrate());
                        contract.setPaymentterms(externalContract.getPaymentterms());
                        contract.setOrderamount(externalContract.getOrderamount());
                        contract.setClosed(externalContract.isClosed());
                        List<PaymentTransaction> paymentTransactions = new ArrayList<>();

                        if (externalContract.getPaymentTransactions() != null) {
                            for (PaymentTransaction externalTransaction : externalContract.getPaymentTransactions()) {
                                PaymentTransaction transaction = new PaymentTransaction();
                                transaction.setPaymenttransactionid(externalTransaction.getPaymenttransactionid());
                                transaction.setAmountdue(externalTransaction.getAmountdue());
                                transaction.setStartingdate(externalTransaction.getStartingdate());
                                transaction.setEnddate(externalTransaction.getEnddate());
                                transaction.setInstallmentnumber(externalTransaction.getInstallmentnumber());
                                transaction.setPaid(externalTransaction.isPaid());
                                transaction.setContract(contract);
                                transaction.setCollected(false);
                                transaction.setReseller(contract.getReseller());
                                transaction.setOrderid(externalTransaction.getOrderid());

                                paymentTransactions.add(paymentTransactionRepository.save(transaction));
                            }

                        }

                        contract.setPaymentTransactions(paymentTransactions); // Update contract with payment transactions

                        // Set other attributes based on your business logic
                        // For relationships, you'll need to populate them as well based on the API data.

                        List<OrderedProduct> orderedProducts = externalContract.getOrderedProducts();
                        System.out.println("Contracts: " + Arrays.toString(contracts));
                        System.out.println("Ordered Products: " + orderedProducts);
                        if (orderedProducts != null) {
                            for (OrderedProduct externalOrderedProduct : orderedProducts) {
                                OrderedProduct orderedProduct = new OrderedProduct();

                                // Map fields from the external API data to your OrderedProduct entity
                                orderedProduct.setOrderedproductid(externalOrderedProduct.getOrderedproductid());
                                orderedProduct.setQuantity(externalOrderedProduct.getQuantity());
                                orderedProduct.setSubtotal(externalOrderedProduct.getSubtotal());

                                // Assuming that OrderedProduct has a ManyToOne relationship with Product
                                // Check if the product information exists in the external data
                                Product externalProduct = externalOrderedProduct.getProduct();
                                if (externalProduct != null) {

                                    Product product = new Product();
                                    System.out.println("External Product Name: " + externalProduct.getName());
                                    product.setName(externalProduct.getName());
                                    product.setUnit(externalProduct.getUnit());
                                    product.setPrice(externalProduct.getPrice());
                                    product.setCommissionrate(externalProduct.getCommissionrate());

                                    //Extra functions
                                    //Long fullPrice = (long) externalProduct.getPrice() * externalOrderedProduct.getQuantity();
                                    Long fullPrice = (long) externalContract.getOrderamount();

                                    //Set fullPrice, itemName and installment_duration(paymentterms)
                                    contract.setItemName(externalProduct.getName());
                                    contract.setFullPrice(fullPrice);




                                    // Set the relationship between OrderedProduct and Product
                                    productRepository.save(product);

                                    orderedProduct.setProduct(product);


                                }else{
                                    System.out.println("External Product is empty!");
                                }


                                // Set the relationship between OrderedProduct and Contract
                                orderedProduct.setContract(contract);

                                // Save the new OrderedProduct entity
                                orderedProductRepository.save(orderedProduct);

                            }

                        }

                        // Add the OrderedProduct entities to the Contract
                        contract.setOrderedProducts(orderedProducts);

                        // Save the new Contract entity
                        contractRepository.save(contract);
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

