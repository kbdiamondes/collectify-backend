package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.Collector;

import javax.persistence.*;

@Entity
@Table(name = "paymentAssurance")
public class PaymentAssurance {
    @Id
    @GeneratedValue
    private Long paymentAssurance_id;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @Column
    private String paymentStatus;

    public PaymentAssurance() {
    }
    public PaymentAssurance(Collector collector, String paymentStatus) {
        this.collector = collector;
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentAssurance_id() {
        return paymentAssurance_id;
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
}
