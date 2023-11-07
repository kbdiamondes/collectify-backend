package com.capstone.collectify.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payment_transactionid;

    @Column
    private String paymenttransactionid;

    @Column
    private double amountdue;

    @Column
    private LocalDate startingdate;

    @Column
    private LocalDate enddate;

    @Column
    private int installmentnumber;

    @Column
    private boolean isPaid;

    @Column
    private boolean isCollected;

    @ManyToOne
    private Contract contract;


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
        isCollected = collected;
    }
}
