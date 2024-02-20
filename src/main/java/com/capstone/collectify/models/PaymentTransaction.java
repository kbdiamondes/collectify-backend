package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_transactionid;

    @Column
    private String orderid;

    @Column
    private String paymenttransactionid;

    @Column
    private double amountdue;

    @Column
    private LocalDate startingdate;
    private LocalDate collectiondate;

    @Column
    private LocalDate enddate;

    @Column
    private int installmentnumber;

    @Column
    private boolean isPaid;

    @Column
    private boolean isCollected;

    @ManyToOne
    @JoinColumn(name="contract_id")
    @JsonBackReference("payment-transactions")
    private Contract contract;

    @OneToOne
    @JoinColumn(name = "transaction_proof_id") // Adjust the column name as needed
    private FileDB transactionProof; // Represents the transaction proof image

    @ManyToOne
    @JoinColumn(name = "collector_id")
    @JsonBackReference("collector-paymenttransactions")
    private Collector collector;

    @ManyToOne
    @JoinColumn(name = "reseller_id")
    @JsonBackReference("reseller-payment-transactions")
    private Reseller reseller;

    public Long getPayment_transactionid() {
        return payment_transactionid;
    }

    public void setPayment_transactionid(Long payment_transactionid) {
        this.payment_transactionid = payment_transactionid;
    }

    public String getPaymenttransactionid() {
        return paymenttransactionid;
    }

    public void setPaymenttransactionid(String paymenttransactionid) {
        this.paymenttransactionid = paymenttransactionid;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public void setAmountdue(double amountdue) {
        this.amountdue = amountdue;
    }

    public LocalDate getStartingdate() {
        return startingdate;
    }

    public void setStartingdate(LocalDate startingdate) {
        this.startingdate = startingdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public int getInstallmentnumber() {
        return installmentnumber;
    }

    public void setInstallmentnumber(int installmentnumber) {
        this.installmentnumber = installmentnumber;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        this.isCollected = collected;
    }

    public FileDB getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(FileDB transactionProof) {
        this.transactionProof = transactionProof;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }


    public LocalDate getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(LocalDate collectiondate) {
        this.collectiondate = collectiondate;
    }
}
