package com.capstone.collectify.models.JoinModule;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contracts {
    @Id
    @GeneratedValue
    private Long contract_id;

    @OneToMany
    @JoinColumn(name="reseller_id", nullable = false)
    private Reseller reseller;

    @OneToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @OneToMany
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @Column
    private String itemName;
    @Column
    private double itemPrice;
    @Column
    private double totalAmountPaid;
    @Column
    private String paymentType;
    @Column
    private String specifications;
    @Column
    private boolean paymentStatus;

    public Contracts() {
    }

    public Contracts(Reseller reseller, Client client, Collector collector, String itemName, double itemPrice, double totalAmountPaid, String paymentType, String specifications, boolean paymentStatus) {
        this.reseller = reseller;
        this.client = client;
        this.collector = collector;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.totalAmountPaid = totalAmountPaid;
        this.paymentType = paymentType;
        this.specifications = specifications;
        this.paymentStatus = paymentStatus;
    }

    public Long getContract_id() {
        return contract_id;
    }
/*

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }
*/

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
