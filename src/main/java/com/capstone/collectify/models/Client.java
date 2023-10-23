package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;

    @Column
    private String username;
    @Column
    private String fullName;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String firstname;

    @Column
    private String middlename;

    @Column
    private String lastname;


    // Other client-specific attributes and relationships
    @OneToMany(mappedBy = "client")
    @JsonManagedReference("client-contracts")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference("client-paymentHistory")
    private List<TransactionHistory> transactionHistory = new ArrayList<>();

    //functions

    // Add a method to add payment history records
    public void addPaymentHistory(TransactionHistory transactionHistoryRecord) {
        transactionHistory.add(transactionHistoryRecord);
        transactionHistoryRecord.setClient(this);
    }

    // Getters and setters
    public Long getClient_id() {
        return client_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    //functions


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
}
