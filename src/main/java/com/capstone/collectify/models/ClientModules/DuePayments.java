package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;

import javax.persistence.*;

@Entity
@Table(name = "duePayments")
public class DuePayments {
    @Id
    @GeneratedValue
    private Long duePayments_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;
    @Column
    private String itemName;

    @Column
    private double requiredCollectible;

    @Column
    private boolean dueStatus;

    public DuePayments() {
    }
    public DuePayments(Client client, String itemName, double requiredCollectible, boolean dueStatus) {
        this.client = client;
        this.itemName = itemName;
        this.requiredCollectible = requiredCollectible;
        this.dueStatus = dueStatus;
    }

    public Long getDuePayments_id() {
        return duePayments_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getRequiredCollectible() {
        return requiredCollectible;
    }

    public void setRequiredCollectible(double requiredCollectible) {
        this.requiredCollectible = requiredCollectible;
    }

    public boolean isDueStatus() {
        return dueStatus;
    }

    public void setDueStatus(boolean dueStatus) {
        this.dueStatus = dueStatus;
    }
}
