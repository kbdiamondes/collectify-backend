package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.Collector;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collectAllDuePayments")
public class CollectAllDuePayments {
    @Id
    @GeneratedValue
    private Long collectAllDuePayments_id;

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
    private String transactionProof;

    public CollectAllDuePayments() {
    }
    public CollectAllDuePayments(Collector collector, Date collectionDate, Double requiredCollectibles, Double itemCollectible, String transactionProof) {
        this.collector = collector;
        this.collectionDate = collectionDate;
        this.requiredCollectibles = requiredCollectibles;
        this.itemCollectible = itemCollectible;
        this.transactionProof = transactionProof;
    }

    public Long getCollectAllDuePayments_id() {
        return collectAllDuePayments_id;
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

    public String getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(String transactionProof) {
        this.transactionProof = transactionProof;
    }
}
