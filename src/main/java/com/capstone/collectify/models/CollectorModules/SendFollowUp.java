package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.Collector;

import javax.persistence.*;

@Entity
@Table(name = "sendFollowUp")
public class SendFollowUp {
    @Id
    @GeneratedValue
    private Long sendFollowUp_id;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @Column
    private String paymentStatus;

    @Column
    private String reminderMessage;

    public SendFollowUp() {
    }
    public SendFollowUp(Collector collector, String paymentStatus, String reminderMessage) {
        this.collector = collector;
        this.paymentStatus = paymentStatus;
        this.reminderMessage = reminderMessage;
    }

    public Long getSendFollowUp_id() {
        return sendFollowUp_id;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }
}
