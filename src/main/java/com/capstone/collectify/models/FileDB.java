package com.capstone.collectify.models;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    @OneToOne(mappedBy = "transactionProof")
    private Contract contract; // Represents the contract associated with this file

    @OneToOne(mappedBy="transactionProof")
    private CollectionHistory collectionHistory;

    @OneToOne(mappedBy="transactionProof")
    private TransactionHistory transactionHistory;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
