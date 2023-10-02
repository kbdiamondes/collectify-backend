package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.Collector;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collectPayments")
public class CollectPayments {
    @Id
    @GeneratedValue
    private Long collectPayments_id;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @Column
    private Date collectionDate;

    @Column
    private Double requiredCollectibles;

    @Column
    private Double itemCollectible;

    @Column
    private String paymentType;

    @Column
    private String transactionProof;

    public CollectPayments() {
    }
    public CollectPayments(Collector collector, Date collectionDate, Double requiredCollectibles, Double itemCollectible, String paymentType, String transactionProof) {
        this.collector = collector;
        this.collectionDate = collectionDate;
        this.requiredCollectibles = requiredCollectibles;
        this.itemCollectible = itemCollectible;
        this.paymentType = paymentType;
        this.transactionProof = transactionProof;
    }

    public Long getCollectPayments_id() {
        return collectPayments_id;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Double getRequiredCollectibles() {
        return requiredCollectibles;
    }

    public void setRequiredCollectibles(Double requiredCollectibles) {
        this.requiredCollectibles = requiredCollectibles;
    }

    public Double getItemCollectible() {
        return itemCollectible;
    }

    public void setItemCollectible(Double itemCollectible) {
        this.itemCollectible = itemCollectible;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(String transactionProof) {
        this.transactionProof = transactionProof;
    }
}
