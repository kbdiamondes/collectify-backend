package com.capstone.collectify.controllers.client;

import com.capstone.collectify.models.SchedulePaymentReminderDTO;
import com.capstone.collectify.services.client.SchedulePaymentReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedule-payment-reminder")
public class SchedulePaymentReminderController {

    @Autowired
    private SchedulePaymentReminderService schedulePaymentReminderService;


    private SchedulePaymentReminderDTO schedulePaymentReminderDTO;

    @GetMapping("/client/{clientId}/reminders")
    public ResponseEntity<List<SchedulePaymentReminderDTO>> getScheduledRemindersForClient(@PathVariable Long clientId) {
        List<SchedulePaymentReminderDTO> reminders = schedulePaymentReminderService.getScheduledPaymentRemindersForClient(clientId);
        return ResponseEntity.ok(reminders);
    }

    @PostMapping("/set-reminder")
    public ResponseEntity<String> setReminder(
            @RequestParam Long clientId,
            @RequestParam Long contractId,
            @RequestParam String reminderTitle,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDateTime) {
        try {
            schedulePaymentReminderService.schedulePaymentReminder(clientId, contractId, reminderTitle, reminderDateTime);
            return ResponseEntity.ok("Payment reminder scheduled successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to set payment reminder: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete-reminder")
    public ResponseEntity<String> deleteReminder(
            @RequestParam Long clientId,
            @RequestParam Long reminderId) {
        try {
            schedulePaymentReminderService.deleteSchedulePaymentReminder(clientId, reminderId);
            return ResponseEntity.ok("Payment reminder deleted successfully");
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete payment reminder: " + e.getMessage());
        }
    }
}
