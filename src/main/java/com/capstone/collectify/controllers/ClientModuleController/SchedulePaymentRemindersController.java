package com.capstone.collectify.controllers.ClientModuleController;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.ClientModules.SchedulePaymentReminders;
import com.capstone.collectify.repositories.ClientRepository;
import com.capstone.collectify.services.ClientModuleServices.SchedulePaymentRemindersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class SchedulePaymentRemindersController {
    @Autowired
    SchedulePaymentRemindersService schedulePaymentRemindersService;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/schedulePaymentReminders/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createSchedulePaymentReminders(@PathVariable Long clientId,@RequestBody SchedulePaymentReminders schedulePaymentReminders) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            schedulePaymentRemindersService.createSchedulePaymentReminders(clientId,schedulePaymentReminders);
            return new ResponseEntity<>("Schedule Payment Reminders created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Schedule Payment Reminders does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/schedulePaymentReminders", method = RequestMethod.GET)
    public ResponseEntity<Object> getSchedulePaymentReminders() {
        return new ResponseEntity<>(schedulePaymentRemindersService.getSchedulePaymentReminders(), HttpStatus.OK);
    }
    @RequestMapping(value="/schedulePaymentReminders/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getSchedulePaymentRemindersByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(schedulePaymentRemindersService.getSchedulePaymentRemindersByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/schedulePaymentReminders/{clientId}/{schedulePaymentReminders_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSchedulePaymentReminders(@PathVariable Long clientId,@PathVariable Long schedulePaymentReminders_id) {
        return schedulePaymentRemindersService.deleteSchedulePaymentReminders(clientId,schedulePaymentReminders_id);
    }

    @RequestMapping(value="/schedulePaymentReminders/{clientId}/{schedulePaymentReminders_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSchedulePaymentReminders(@PathVariable Long clientId,@PathVariable Long schedulePaymentReminders_id, @RequestBody SchedulePaymentReminders schedulePaymentReminders) {
        return schedulePaymentRemindersService.updateSchedulePaymentReminders(clientId,schedulePaymentReminders_id, schedulePaymentReminders);
    }
}