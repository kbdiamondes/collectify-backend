package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String orderId;


    @Column
    private BigDecimal amountPaid;

    @Column
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name="payment_transactionProof")
    private FileDB transactionProof;

    private String productName;

    private String clientName;

    private String collectorName;


    // Add a reference to the Client entity
    @ManyToOne
    @JsonBackReference("client-paymentHistory")
    private Client client;

    @ManyToOne
    @JsonBackReference("contract-transactionHistory")
    @JoinColumn(name = "contract_id")
    private Contract contract;

    // Constructors, getters, and setters

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    public FileDB getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(FileDB transactionProof) {
        this.transactionProof = transactionProof;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }
}
