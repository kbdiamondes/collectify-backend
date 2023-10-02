package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.CollectorModules.CollectPayments;

import javax.persistence.*;

@Entity
@Table(name = "clientPaymentRecords")
public class ClientPaymentRecords {
    @Id
    @GeneratedValue
    private Long clientPaymentRecords_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

   /* @Column
    private PayDues payDues;*/
    @OneToOne
    @JoinColumn(name="payDues_id", nullable = false)
    private PayDues payDues;


    /*@Column
    private CollectPayments collectPayments;*/

    @OneToOne
    @JoinColumn(name="collectPayments_id", nullable = false)
    private CollectPayments collectPayments;

    public ClientPaymentRecords() {
    }
    public ClientPaymentRecords(Client client, PayDues payDues, CollectPayments collectPayments) {
        this.client = client;
        this.payDues = payDues;
        this.collectPayments = collectPayments;
    }

    public Long getClientPaymentRecords_id() {
        return clientPaymentRecords_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PayDues getPayDues() {
        return payDues;
    }

    public void setPayDues(PayDues payDues) {
        this.payDues = payDues;
    }

    public CollectPayments getCollectPayments() {
        return collectPayments;
    }

    public void setCollectPayments(CollectPayments collectPayments) {
        this.collectPayments = collectPayments;
    }
}
