package com.capstone.collectify.repositories;

import com.capstone.collectify.models.SchedulePaymentReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchedulePaymentReminderRepository extends JpaRepository<SchedulePaymentReminder, Long> {
    @Query("SELECT r FROM SchedulePaymentReminder r " +
            "JOIN r.paymentTransaction pt " +
            "WHERE pt.contract.client.client_id = :clientId")
    List<SchedulePaymentReminder> findRemindersByClientId(Long clientId);
}
