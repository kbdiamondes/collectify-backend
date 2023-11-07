package com.capstone.collectify.services.client;

import com.capstone.collectify.models.*;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;

import com.capstone.collectify.repositories.PaymentTransactionRepository;
import com.capstone.collectify.repositories.SchedulePaymentReminderRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulePaymentReminderServiceImpl implements SchedulePaymentReminderService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SchedulePaymentReminderRepository schedulePaymentReminderRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;



    @Override
    public List<SchedulePaymentReminderDTO> getScheduledPaymentRemindersForClient(Long clientId) {
        List<SchedulePaymentReminder> reminders = schedulePaymentReminderRepository.findRemindersByClientId(clientId);
        return reminders.stream()
                .map(this::mapReminderToDTO)
                .collect(Collectors.toList());
    }

    private SchedulePaymentReminderDTO mapReminderToDTO(SchedulePaymentReminder reminder) {
        SchedulePaymentReminderDTO dto = new SchedulePaymentReminderDTO();
        dto.setId(reminder.getId());
        dto.setReminderTitle(reminder.getReminderTitle());
        dto.setReminderDateTime(reminder.getReminderDateTime());

        PaymentTransaction paymentTransaction = reminder.getPaymentTransaction();
        if (paymentTransaction != null) {
            // Set the dueAmount in the DTO from PaymentTransaction's amount due
            dto.setDueAmount(BigDecimal.valueOf(paymentTransaction.getAmountdue()));
            dto.setPaid(paymentTransaction.isPaid());
        }

        return dto;
    }

    @Override
    public void schedulePaymentReminder(Long clientId, Long paymentTransactionId, String reminderTitle, LocalDateTime reminderDateTime) throws AccessDeniedException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        PaymentTransaction paymentTransaction = paymentTransactionRepository.findById(paymentTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment transaction not found with id: " + paymentTransactionId));

        if (paymentTransaction.getContract().getClient().equals(client)) {
            SchedulePaymentReminder reminder = new SchedulePaymentReminder();
            reminder.setPaymentTransaction(paymentTransaction);
            reminder.setReminderTitle(reminderTitle);
            reminder.setReminderDateTime(reminderDateTime);
            schedulePaymentReminderRepository.save(reminder);
        } else {
            throw new AccessDeniedException("You don't have permission to set a reminder for this payment transaction.");
        }
    }


    @Override
    public void deleteSchedulePaymentReminder(Long clientId, Long reminderId) throws AccessDeniedException {
        SchedulePaymentReminder reminder = schedulePaymentReminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + reminderId));

        if (reminder.getPaymentTransaction().getContract().getClient().getClient_id().equals(clientId)) {
            schedulePaymentReminderRepository.deleteById(reminderId);
        } else {
            throw new AccessDeniedException("You don't have permission to delete this reminder.");
        }
    }



}
