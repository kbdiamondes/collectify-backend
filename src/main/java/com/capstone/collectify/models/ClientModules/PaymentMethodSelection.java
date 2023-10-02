package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;

import javax.persistence.*;

@Entity
@Table(name = "paymentMethodSelection")
public class PaymentMethodSelection {
    @Id
    @GeneratedValue
    private Long paymentMethodSelection_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;
    @Column
    private String purchaseMethod;

    @Column
    private String itemName;

    @Column
    private String itemPrice;

    @Column
    private String itemSpecs;

    @Column
    private double installmentAmount;

    public PaymentMethodSelection() {
    }
    public PaymentMethodSelection(Client client, String purchaseMethod, String itemName, String itemPrice, String itemSpecs, double installmentAmount) {
        this.client = client;
        this.purchaseMethod = purchaseMethod;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSpecs = itemSpecs;
        this.installmentAmount = installmentAmount;
    }

    public Long getPaymentMethodSelection_id() {
        return paymentMethodSelection_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPurchaseMethod() {
        return purchaseMethod;
    }

    public void setPurchaseMethod(String purchaseMethod) {
        this.purchaseMethod = purchaseMethod;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSpecs() {
        return itemSpecs;
    }

    public void setItemSpecs(String itemSpecs) {
        this.itemSpecs = itemSpecs;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }
}
