package com.capstone.collectify.models.ClientModules;

import com.capstone.collectify.models.Client;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedulePaymentReminders")
public class SchedulePaymentReminders {
    @Id
    @GeneratedValue
    private Long schedulePaymentReminders_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name="paymentDues_id", nullable = false)
    private DuePayments paymentDues;

    @Column
    private Date collectionDate;

    @Column
    private long productTypeID;

    @Column
    private String reminderTitle;

    @Column
    private Date reminderDate;

    public SchedulePaymentReminders() {
    }
    public SchedulePaymentReminders(Client client, DuePayments paymentDues, Date collectionDate, long productTypeID, String reminderTitle, Date reminderDate) {
        this.client = client;
        this.paymentDues = paymentDues;
        this.collectionDate = collectionDate;
        this.productTypeID = productTypeID;
        this.reminderTitle = reminderTitle;
        this.reminderDate = reminderDate;
    }

    public Long getSchedulePaymentReminders_id() {
        return schedulePaymentReminders_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DuePayments getPaymentDues() {
        return paymentDues;
    }

    public void setPaymentDues(DuePayments paymentDues) {
        this.paymentDues = paymentDues;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public long getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(long productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }
}
