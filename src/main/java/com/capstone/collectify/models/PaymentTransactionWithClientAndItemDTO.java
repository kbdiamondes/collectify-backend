package com.capstone.collectify.models;

import java.time.LocalDate;

public class PaymentTransactionWithClientAndItemDTO {
    // Fields from PaymentTransaction entity
    private Long payment_transactionid;
    private String orderid;
    private String paymenttransactionid;
    private double amountdue;
    private LocalDate startingdate;
    private LocalDate collectiondate;
    private LocalDate enddate;
    private int installmentnumber;
    private boolean paid;
    private boolean collected;

    // Additional fields
    private String clientName;
    private String itemName;

    // Constructor
    public PaymentTransactionWithClientAndItemDTO(PaymentTransaction paymentTransaction, String clientName, String itemName) {
        // Initialize fields from PaymentTransaction
        this.payment_transactionid = paymentTransaction.getPayment_transactionid();
        this.orderid = paymentTransaction.getOrderid();
        this.paymenttransactionid = paymentTransaction.getPaymenttransactionid();
        this.amountdue = paymentTransaction.getAmountdue();
        this.startingdate = paymentTransaction.getStartingdate();
        this.collectiondate = paymentTransaction.getCollectiondate();
        this.enddate = paymentTransaction.getEnddate();
        this.installmentnumber = paymentTransaction.getInstallmentnumber();
        this.paid = paymentTransaction.isPaid();
        this.collected = paymentTransaction.isCollected();

        // Set clientName and itemName
        this.clientName = clientName;
        this.itemName = itemName;
    }

    // Getters and Setters


    public Long getPayment_transactionid() {
        return payment_transactionid;
    }

    public void setPayment_transactionid(Long payment_transactionid) {
        this.payment_transactionid = payment_transactionid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public LocalDate getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(LocalDate collectiondate) {
        this.collectiondate = collectiondate;
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
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
