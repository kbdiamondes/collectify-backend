package com.capstone.collectify.models;

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
    private String address;

    @Column
    private String email;

    @Column
    private String password;

    // Other reseller-specific attributes and relationships

    @OneToMany(mappedBy = "reseller")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "reseller")
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


    // Getters and setters
}
