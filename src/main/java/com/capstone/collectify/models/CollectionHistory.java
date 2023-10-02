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

    // Other collection history-specific attributes

    @ManyToOne
    @JsonBackReference("reseller_collectionHistory")
    private Reseller reseller;

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
}
