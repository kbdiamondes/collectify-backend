package com.capstone.collectify.models;

import javax.persistence.*;

@Entity
@Table(name = "collector")
public class Collector {
    @Id
    @GeneratedValue
    private Long collector_id;

    @ManyToOne
    @JoinColumn(name="reseller_id", nullable = false)
    private Reseller reseller;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private String address;

    @Column
    private String email;

 /*   @OneToMany(mappedBy = "collector")
    @JsonIgnore
    private Set<SendCollectors> sendCollectors;*/

    public Collector() {
    }

    public Collector(Reseller reseller, String username, String password, String fullName, String address, String email) {
        this.reseller = reseller;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getCollector_id() {
        return collector_id;
    }

 /*   public Set<SendCollectors> getSendCollectors() {
        return sendCollectors;
    }

    public void setSendCollectors(Set<SendCollectors> sendCollectors) {
        this.sendCollectors = sendCollectors;
    }*/
}
