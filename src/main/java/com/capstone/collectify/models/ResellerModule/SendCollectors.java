package com.capstone.collectify.models.ResellerModule;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;

import javax.persistence.*;
    @Entity
    @Table(name = "sendCollectors")
    public class SendCollectors {
        @Id
        @GeneratedValue
        private Long sendCollector_id;

        @Column
        private double paymentDues;

        @ManyToOne
        @JoinColumn(name="reseller_id", nullable = false)
        private Reseller reseller;

        @ManyToOne
        @JoinColumn(name="collector_id", nullable = false)
        private Collector collector;

        @ManyToOne
        @JoinColumn(name="client_id", nullable = false)
        private Client client;

        public SendCollectors() {
        }
        public SendCollectors(double paymentDues, Reseller reseller, Collector collector, Client client) {
            this.paymentDues = paymentDues;
            this.reseller = reseller;
            this.collector = collector;
            this.client = client;
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

        public void setSendCollector_id(long sendCollector_id) {
            this.sendCollector_id = sendCollector_id;
        }

    /*    public Collector getCollector() {
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
        }*/

        public Long getSendCollector_id() {
            return sendCollector_id;
        }

        public Reseller getReseller() {
            return reseller;
        }

        public void setReseller(Reseller reseller) {
            this.reseller = reseller;
        }

        public double getPaymentDues() {
            return paymentDues;
        }

        public void setPaymentDues(double paymentDues) {
            this.paymentDues = paymentDues;
        }
}
