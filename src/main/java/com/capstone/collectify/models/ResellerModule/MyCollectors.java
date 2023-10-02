package com.capstone.collectify.models.ResellerModule;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;

import javax.persistence.*;


@Entity
@Table(name = "mycollectors")
public class MyCollectors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mycollectors_id")
    private Long myCollectorsId;

    @Column(name = "collection_status")
    private Boolean collectionStatus;

    @Column(name = "required_collectibles")
    private double requiredCollectibles;

    @ManyToOne
    @JoinColumn(name="reseller_id", nullable = false)
    private Reseller reseller;

    @ManyToOne
    @JoinColumn(name="collector_id", nullable = false)
    private Collector collector;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;


    public MyCollectors() {
    }

    public MyCollectors(Boolean collectionStatus, double requiredCollectibles, Reseller reseller, Collector collector, Client client) {
        this.collectionStatus = collectionStatus;
        this.requiredCollectibles = requiredCollectibles;
        this.reseller = reseller;
        this.collector = collector;
        this.client = client;
    }

    public Long getMyCollectorsId() {
        return myCollectorsId;
    }

    public Boolean getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Boolean collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public double getRequiredCollectibles() {
        return requiredCollectibles;
    }

    public void setRequiredCollectibles(double requiredCollectibles) {
        this.requiredCollectibles = requiredCollectibles;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
