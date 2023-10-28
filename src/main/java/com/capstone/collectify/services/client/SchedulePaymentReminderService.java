package com.capstone.collectify.services.client;

import com.capstone.collectify.models.SchedulePaymentReminderDTO;

import javax.persistence.Access;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

public interface SchedulePaymentReminderService {
    void schedulePaymentReminder(Long clientId, Long contractId, String reminderTitle, LocalDateTime reminderDateTime) throws AccessDeniedException;
    List<SchedulePaymentReminderDTO> getScheduledPaymentRemindersForClient(Long clientId);

    void deleteSchedulePaymentReminder(Long clientId, Long reminderId) throws AccessDeniedException;
}
