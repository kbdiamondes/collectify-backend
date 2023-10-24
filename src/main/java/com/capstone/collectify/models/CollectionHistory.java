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



    // Other collection history-specific attributes


    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonBackReference("client_paymentHistory") // Adjust the reference name as needed
    private Client client;

    @ManyToOne
    @JoinColumn(name="reseller_id")
    @JsonBackReference("reseller_collectionHistory")
    private Reseller reseller;

    @ManyToOne
    @JoinColumn(name="collector_id")
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
}
