package com.capstone.collectify.models.CollectorModules;

import com.capstone.collectify.models.ClientModules.PayDues;
import com.capstone.collectify.models.Collector;

import javax.persistence.*;

@Entity
@Table(name = "collectorCollectionRecords")
public class collectorCollectionRecords {
    @Id
    @GeneratedValue
    private Long collectorCollectionRecords_id;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

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



    public collectorCollectionRecords() {
    }

    public collectorCollectionRecords(Collector collector, PayDues payDues, CollectPayments collectPayments) {
        this.collector = collector;
        this.payDues = payDues;
        this.collectPayments = collectPayments;
    }


    public Long getCollectorCollectionRecords_id() {
        return collectorCollectionRecords_id;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
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
