package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collector_id;
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
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy="collector")
    @JsonManagedReference("collector-assignedcontract")
    private List<Contract> assignedContract = new ArrayList<>();

    @OneToMany(mappedBy = "collector")
    @JsonManagedReference("collector-paymenttransactions")
    private List<PaymentTransaction> assignedPaymentTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "collector")
    @JsonManagedReference("collector_collectionHistory")
    private List<CollectionHistory> collectionHistory;

    @ManyToMany
    @JoinTable(
            name = "collector_reseller",
            joinColumns = @JoinColumn(name = "collector_id"),
            inverseJoinColumns = @JoinColumn(name = "reseller_id")
    )
    private List<Reseller> resellers;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getCollector_id() {
        return collector_id;
    }

    public void setCollector_id(Long collector_id) {
        this.collector_id = collector_id;
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

    public List<Contract> getAssignedContract() {
        return assignedContract;
    }

    public void setAssignedContract(List<Contract> assignedContract) {
        this.assignedContract = assignedContract;
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

    public List<PaymentTransaction> getAssignedPaymentTransactions() {
        return assignedPaymentTransactions;
    }

    public void setAssignedPaymentTransactions(List<PaymentTransaction> assignedPaymentTransactions) {
        this.assignedPaymentTransactions = assignedPaymentTransactions;
    }

    public List<CollectionHistory> getCollectionHistory() {
        return collectionHistory;
    }

    public void setCollectionHistory(List<CollectionHistory> collectionHistory) {
        this.collectionHistory = collectionHistory;
    }

    public List<Reseller> getResellers() {
        return resellers;
    }

    public void setResellers(List<Reseller> resellers) {
        this.resellers = resellers;
    }
}
