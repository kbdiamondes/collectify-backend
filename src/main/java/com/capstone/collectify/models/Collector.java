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
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy="collector")
    @JsonManagedReference("collector-assignedcontract")
    private List<Contract> assignedContract = new ArrayList<>();

    @OneToMany(mappedBy = "collector")
    @JsonManagedReference("collector_collectionHistory")
    private List<CollectionHistory> collectionHistory;

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

/*
    public void setAssignedClient(Client client) {
        // Implement logic to set the assigned client by creating a new contract
        if (client != null) {
            Contract newContract = new Contract();
            newContract.setClient(client);
            newContract.setCollector(this);

            // You may need to set other contract properties such as dueAmount and isPaid
            // newContract.setDueAmount(dueAmount);
            // newContract.setIsPaid(isPaid);

            this.assignedContract = newContract;
        } else {
            // If client is null, clear the assigned contract
            this.assignedContract = null;
        }
    }

    public Client getAssignedClient() {
        // Implement logic to get the assigned client from the assigned contract
        if (assignedContract != null) {
            return assignedContract.getClient();
        } else {
            return null; // No client is assigned
        }
    }*/

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
