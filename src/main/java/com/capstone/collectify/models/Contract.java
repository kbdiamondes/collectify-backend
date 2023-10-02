package com.capstone.collectify.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Contract {
    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Long getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Long fullPrice) {
        this.fullPrice = fullPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Reseller getReseller() {
        return reseller;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @Column
    private String username;

    @Column
    private String itemName;

    @Column
    private BigDecimal dueAmount;

    @Column
    private Long fullPrice;

    @Column
    private boolean isPaid;

    // Other contract-specific attributes and relationships

    @ManyToOne
    private Client client;

    @ManyToOne
    private Reseller reseller;

    @OneToOne(mappedBy = "assignedContract")
    private Collector collector;


}
