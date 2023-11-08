package com.capstone.collectify.models;

import java.time.LocalDate;

public class PaymentTransactionWithItemNameDTO {
    private Long payment_transactionid;
    private String orderid;
    private String paymenttransactionid;
    private double amountdue;
    private LocalDate startingdate;
    private LocalDate enddate;
    private int installmentnumber;
    private boolean paid;
    private boolean collected;
    private String itemName; // Add this field

    public PaymentTransactionWithItemNameDTO(PaymentTransaction paymentTransaction, String itemName) {
        this.payment_transactionid = paymentTransaction.getPayment_transactionid();
        this.orderid = paymentTransaction.getOrderid();
        this.paymenttransactionid = paymentTransaction.getPaymenttransactionid();
        this.amountdue = paymentTransaction.getAmountdue();
        this.startingdate = paymentTransaction.getStartingdate();
        this.enddate = paymentTransaction.getEnddate();
        this.installmentnumber = paymentTransaction.getInstallmentnumber();
        this.paid = paymentTransaction.isPaid();
        this.collected = paymentTransaction.isCollected();
        this.itemName = itemName;
    }

    /* Getters and Setters for the fields */

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}

