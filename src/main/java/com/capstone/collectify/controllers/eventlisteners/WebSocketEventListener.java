package com.capstone.collectify.controllers.eventlisteners;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
@Component
public class WebSocketEventListener {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ResellerRepository resellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderedProductRepository orderedProductRepository;

    @MessageMapping("/app/topic/contract-updates") // This should match the WebSocket endpoint where you receive updates
    public void handleApiUpdates(@Payload Contract updatedContract) {
        // Process the updatedContract and save it to your database
        // Ensure you have all the necessary repositories and services injected here
        saveNewContract(updatedContract);
    }

    private void saveNewContract(Contract updatedContract) {
        if (updatedContract != null) {
            // Check if the contract already exists in the database using the external order ID
            if (!contractRepository.existsByOrderid(updatedContract.getOrderid())) {
                // Create a new Contract entity
                Contract newContract = new Contract();

                // Check if the distributor (reseller) information exists in the external data
                Reseller externalDistributor = updatedContract.getReseller();
                if (externalDistributor != null) {
                    Reseller reseller = new Reseller();
                    // Map and set distributor attributes
                    String username = externalDistributor.getFirstname() + "." + externalDistributor.getLastname();

                    reseller.setFullName(externalDistributor.getFirstname() + " " + externalDistributor.getMiddlename() + " " + externalDistributor.getLastname());
                    reseller.setUsername(username);
                    reseller.setFirstname(externalDistributor.getFirstname());
                    reseller.setMiddlename(externalDistributor.getMiddlename());
                    reseller.setLastname(externalDistributor.getLastname());
                    reseller.setEmail(externalDistributor.getEmail());
                    reseller.setAddress(externalDistributor.getAddress());
                    reseller.setPassword(externalDistributor.getPassword());

                    // ...
                    resellerRepository.save(reseller);
                    newContract.setReseller(reseller);
                } else {
                    // External distributor is null, set the field to null
                    newContract.setReseller(null);
                }

                // Check if the dealer (client) information exists in the external data
                Client externalDealer = updatedContract.getClient();
                if (externalDealer != null) {
                    Client client = new Client();
                    // Map and set dealer attributes

                    String username = externalDealer.getFirstname() + "." + externalDealer.getLastname();

                    client.setFullName(externalDealer.getFirstname() + " " + externalDealer.getMiddlename() + " " + externalDealer.getLastname());
                    client.setUsername(username);
                    client.setFirstname(externalDealer.getFirstname());
                    client.setMiddlename(externalDealer.getMiddlename());
                    client.setLastname(externalDealer.getLastname());
                    client.setEmail(externalDealer.getEmail());
                    client.setAddress(externalDealer.getAddress());
                    client.setPassword(externalDealer.getPassword());

                    //Set Contract Client's username
                    newContract.setUsername(username);

                    // ...
                    clientRepository.save(client);
                    newContract.setClient(client);
                } else {
                    // External client is null, set the field to null
                    newContract.setClient(null);
                }

                // Map fields from the external API data to your Contract entity
                newContract.setOrderid(updatedContract.getOrderid());
                newContract.setOrderdate(updatedContract.getOrderdate());
                newContract.setDistributiondate(updatedContract.getDistributiondate());
                newContract.setPenaltyrate(updatedContract.getPenaltyrate());
                newContract.setPaymentterms(updatedContract.getPaymentterms());
                newContract.setOrderamount(updatedContract.getOrderamount());

                // Set other attributes based on your business logic
                // For relationships, you'll need to populate them as well based on the API data.

                List<OrderedProduct> orderedProducts = updatedContract.getOrderedProducts();
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
                            product.setName(externalProduct.getName());
                            product.setUnit(externalProduct.getUnit());
                            product.setPrice(externalProduct.getPrice());
                            product.setCommissionrate(externalProduct.getCommissionrate());

                            // Extra functions
                            Long fullPrice = (long) externalProduct.getPrice() * externalOrderedProduct.getQuantity();
                            int installmentduration = updatedContract.getPaymentterms();

                            // Set dueAmount, fullPrice, itemName, and installment_duration(paymentterms)
                            newContract.setItemName(externalProduct.getName());
                            newContract.setFullPrice(fullPrice);
                            newContract.setInstallmentDuration(installmentduration);
                            newContract.setDueAmount(BigDecimal.valueOf(fullPrice / installmentduration));

                            if (installmentduration != 0) {
                                newContract.setIsMonthly(true);
                            } else {
                                newContract.setIsMonthly(false);
                            }

                            // Set the relationship between OrderedProduct and Product
                            productRepository.save(product);
                            orderedProduct.setProduct(product);
                        }

                        // Set the relationship between OrderedProduct and Contract
                        orderedProduct.setContract(newContract);

                        // Save the new OrderedProduct entity
                        orderedProductRepository.save(orderedProduct);
                    }
                }

                // Add the OrderedProduct entities to the Contract
                newContract.setOrderedProducts(orderedProducts);

                // Save the new Contract entity
                contractRepository.save(newContract);
            }
        }
    }

}
