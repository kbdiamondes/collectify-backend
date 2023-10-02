package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;

import javax.persistence.*;

@Entity
@Table(name = "payDues")
public class PayDues {
    @Id
    @GeneratedValue
    private Long payDues_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

   /* @Column
    private PaymentDues paymentDues;*/

    @OneToOne
    @JoinColumn(name="duePayments", nullable = false)
    private DuePayments duePayments;

    @Column
    private double itemPrice;

    @Column
    private String referenceNumber;

    @Column
    private String paymentType;

    @Column
    private String transactionProof;

    public PayDues() {
    }
    public PayDues(Client client, DuePayments duePayments, double itemPrice, String referenceNumber, String paymentType, String transactionProof) {
        this.client = client;
        this.duePayments = duePayments;
        this.itemPrice = itemPrice;
        this.referenceNumber = referenceNumber;
        this.paymentType = paymentType;
        this.transactionProof = transactionProof;
    }

    public Long getPayDues_id() {
        return payDues_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DuePayments getDuePayments() {
        return duePayments;
    }

    public void setDuePayments(DuePayments duePayments) {
        this.duePayments = duePayments;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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
