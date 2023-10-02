package com.capstone.collectify.models;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private Long client_id;

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

   /* @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<SendCollectors> sendCollectors;*/

    public Client() {
        }
    public Long getClient_id() {
        return client_id;
        }
    public Client(String username, String password, String fullName, String address, String email) {
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.address = address;
            this.email = email;
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


   /* public Set<SendCollectors> getSendCollectors() {
        return sendCollectors;
    }

    public void setSendCollectors(Set<SendCollectors> sendCollectors) {
        this.sendCollectors = sendCollectors;
    }*/
}
