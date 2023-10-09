package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @Column
    private String username;

    @Column
    private String itemName;

    @Column
    private BigDecimal dueAmount;

    @Column
    private Long fullPrice;

    @Column
    private boolean isPaid;

    @Column
    private int installmentDuration;

    @Column
    private boolean isMonthly;


    @Column
    private LocalDateTime lastPaymentDate;


    // Other contract-specific attributes and relationships

    @ManyToOne
    @JsonBackReference("client-contracts")
    private Client client;

    @ManyToOne
    @JsonBackReference("reseller-contracts")
    private Reseller reseller;

    @OneToOne(mappedBy = "assignedContract")
    @JsonBackReference("collector-assignedcontract")
    private Collector collector;

    @OneToOne
    @JoinColumn(name = "transaction_proof_id") // Adjust the column name as needed
    private FileDB transactionProof; // Represents the transaction proof image

    public FileDB getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(FileDB transactionProof) {
        this.transactionProof = transactionProof;
    }

    public Long getContract_id() {
        return contract_id;
    }

    public void setContract_id(Long contract_id) {
        this.contract_id = contract_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Long getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Long fullPrice) {
        this.fullPrice = fullPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public int getInstallmentDuration() {
        return installmentDuration;
    }

    public void setInstallmentDuration(int installmentDuration) {
        this.installmentDuration = installmentDuration;
    }

    public boolean isIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    public LocalDateTime getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDateTime lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }



    //functions
    //Used in ContractServiceImpl
    public BigDecimal calculateMonthlyInstallmentAmount(Boolean isMonthly) {
        if (isMonthly) {
            if (installmentDuration > 0) {
                return dueAmount.divide(BigDecimal.valueOf(installmentDuration), 2, RoundingMode.HALF_UP);
            } else {
                throw new IllegalArgumentException("Invalid installment duration");
            }
        } else {
            return dueAmount; // For non-monthly payments, the due amount remains the same
        }
    }
}
