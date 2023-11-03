package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CollectionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectionhistory_id;
    private BigDecimal collectedAmount;
    private LocalDateTime collectionDate;

    private String paymentType;

    private String orderid;
    private String itemName;

    private String reseller_username;

    private String client_username;

    private String collector_username;

    @OneToOne
    @JoinColumn(name = "transaction_proof_id")
    private FileDB transactionProof;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    public FileDB getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(FileDB transactionProof) {
        this.transactionProof = transactionProof;
    }

    @ManyToOne
    @JsonBackReference("client_paymentHistory") // Adjust the reference name as needed
    private Client client;

    @ManyToOne
    @JsonBackReference("reseller_collectionHistory")
    private Reseller reseller;

    @ManyToOne
    @JsonBackReference("collector_collectionHistory")
    private Collector collector;

    // Getters and setters

    public Long getId() {
        return collectionhistory_id;
    }

    public void setId(Long id) {
        this.collectionhistory_id = id;
    }

    public BigDecimal getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(BigDecimal collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public LocalDateTime getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDateTime collectionDate) {
        this.collectionDate = collectionDate;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getReseller_username() {
        return reseller_username;
    }

    public void setReseller_username(String reseller_username) {
        this.reseller_username = reseller_username;
    }

    public String getClient_username() {
        return client_username;
    }

    public void setClient_username(String client_username) {
        this.client_username = client_username;
    }

    public String getCollector_username() {
        return collector_username;
    }

    public void setCollector_username(String collector_username) {
        this.collector_username = collector_username;
    }
}
