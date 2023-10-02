package com.capstone.collectify.services.ClientModuleServices;

import com.capstone.collectify.models.ClientModules.SchedulePaymentReminders;
import org.springframework.http.ResponseEntity;

public interface SchedulePaymentRemindersService {
    void createSchedulePaymentReminders(Long client,SchedulePaymentReminders schedulePaymentReminders);

    Iterable<SchedulePaymentReminders> getSchedulePaymentReminders();

    Iterable<SchedulePaymentReminders>getSchedulePaymentRemindersByClientId(Long clientId);

    ResponseEntity deleteSchedulePaymentReminders(Long clientId,Long id);

    ResponseEntity updateSchedulePaymentReminders(Long clientId, Long id, SchedulePaymentReminders schedulePaymentReminders);
}
