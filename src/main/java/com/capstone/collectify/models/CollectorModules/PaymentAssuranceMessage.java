package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.ClientModules.ChatCollector;
import com.capstone.collectify.models.Collector;

import javax.persistence.*;

@Entity
@Table(name = "paymentAssuranceMessage")
public class PaymentAssuranceMessage {
    @Id
    @GeneratedValue
    private Long paymentAssuranceMessage_id;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @Column
    private String collectorMessage;


  @OneToOne
    @JoinColumn(name="chatCollector_id", nullable = false)
    private ChatCollector chatCollector;
/* @Column
    private ChatCollector chatCollector;*/

    public PaymentAssuranceMessage() {
    }
    public PaymentAssuranceMessage(Collector collector, String collectorMessage,ChatCollector chatCollector) {
        this.collector = collector;
        this.collectorMessage = collectorMessage;
        this.chatCollector = chatCollector;
    }

    public Long getPaymentAssuranceMessage_id() {
        return paymentAssuranceMessage_id;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public String getCollectorMessage() {
        return collectorMessage;
    }

    public void setCollectorMessage(String collectorMessage) {
        this.collectorMessage = collectorMessage;
    }

   public ChatCollector getChatCollector() {
        return chatCollector;
    }

    public void setChatCollector(ChatCollector chatCollector) {
        this.chatCollector = chatCollector;
    }
}
