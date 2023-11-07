package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.SchedulePaymentReminder;
import com.capstone.collectify.models.SchedulePaymentReminderDTO;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.repositories.ContractRepository;

import com.capstone.collectify.repositories.SchedulePaymentReminderRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Contract contract = reminder.getContract();
        if (contract != null) {
            //dto.setDueAmount(contract.getDueAmount());
            //dto.setPaid(contract.isPaid());
            // Add more contract-related information if needed
        }

        return dto;
    }


    @Override
    public void schedulePaymentReminder(Long clientId, Long contractId, String reminderTitle, LocalDateTime reminderDateTime) throws AccessDeniedException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + clientId));

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + contractId));

        if (contract.getClient().equals(client)) {
            SchedulePaymentReminder reminder = new SchedulePaymentReminder();
            reminder.setContract(contract);
            reminder.setReminderTitle(reminderTitle);
            reminder.setReminderDateTime(reminderDateTime);
            schedulePaymentReminderRepository.save(reminder);
        } else {
            throw new AccessDeniedException("You don't have permission to set a reminder for this contract.");
        }
    }

    @Override
    public void deleteSchedulePaymentReminder(Long clientId, Long reminderId) throws AccessDeniedException {
        // Validate if the reminder belongs to the given client
        SchedulePaymentReminder reminder = schedulePaymentReminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + reminderId));

        if (reminder.getContract().getClient().getClient_id().equals(clientId)) {
            schedulePaymentReminderRepository.deleteById(reminderId);
        } else {
            throw new AccessDeniedException("You don't have permission to delete this reminder.");
        }
    }



}
