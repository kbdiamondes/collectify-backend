package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reseller_id;
    @Column
    private String username;

    @Column
    private String firstname;

    @Column
    private String middlename;

    @Column
    private String lastname;

    @Column
    private String fullName;


    @Column
    private String address;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    // Other reseller-specific attributes and relationships

    @OneToMany(mappedBy = "reseller")
    @JsonManagedReference("reseller-contracts")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "reseller")
    @JsonManagedReference("reseller-payment-transactions")
    private List<PaymentTransaction> paymentTransactions;


    @OneToMany(mappedBy = "reseller")
    @JsonManagedReference("reseller_collectionHistory")
    private List<CollectionHistory> collectionHistory;

    public Long getId() {
        return reseller_id;
    }

    public void setId(Long id) {
        this.reseller_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<CollectionHistory> getCollectionHistory() {
        return collectionHistory;
    }

    public void setCollectionHistory(List<CollectionHistory> collectionHistory) {
        this.collectionHistory = collectionHistory;
    }

    public Long getReseller_id() {
        return reseller_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

// Getters and setters


    public void setReseller_id(Long reseller_id) {
        this.reseller_id = reseller_id;
    }

    public List<PaymentTransaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }
}
