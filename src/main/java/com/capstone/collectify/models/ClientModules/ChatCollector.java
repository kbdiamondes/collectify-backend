package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.CollectorModules.PaymentAssurance;

import javax.persistence.*;

@Entity
@Table(name = "chatCollector")
public class ChatCollector {
    @Id
    @GeneratedValue
    private Long chatCollector_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    /*@Column
    private PaymentAssurance paymentAssurance;*/
    @OneToOne
    @JoinColumn(name="paymentAssurance_id", nullable = false)
    private PaymentAssurance paymentAssurance;

    @Column
    private String clientMessage;

    public ChatCollector() {
    }
    public ChatCollector(Client client, PaymentAssurance paymentAssurance, String clientMessage) {
        this.client = client;
        this.paymentAssurance = paymentAssurance;
        this.clientMessage = clientMessage;
    }

    public Long getChatCollector_id() {
        return chatCollector_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PaymentAssurance getPaymentAssurance() {
        return paymentAssurance;
    }

    public void setPaymentAssurance(PaymentAssurance paymentAssurance) {
        this.paymentAssurance = paymentAssurance;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }
}
