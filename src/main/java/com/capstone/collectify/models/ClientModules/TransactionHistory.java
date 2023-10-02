package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactionHistory")
public class TransactionHistory {
    @Id
    @GeneratedValue
    private Long transactionHistory_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @Column
    private Date transactionDate;

    @Column
    private double amountSent;



    public TransactionHistory() {
    }
    public TransactionHistory(Client client, Date transactionDate, double amountSent) {
        this.client = client;
        this.transactionDate = transactionDate;
        this.amountSent = amountSent;
    }

    public Long getTransactionHistory_id() {
        return transactionHistory_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmountSent() {
        return amountSent;
    }

    public void setAmountSent(double amountSent) {
        this.amountSent = amountSent;
    }
}
