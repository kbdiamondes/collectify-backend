package com.capstone.collectify.services.CollectorModuleServices;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.SendFollowUp;
import com.capstone.collectify.repositories.CollectorModuleRepository.SendFollowUpRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SendFollowUpServiceImpl implements SendFollowUpService{
    @Autowired
    private SendFollowUpRepository sendFollowUpRepository;
    @Autowired
    private CollectorRepository collectorRepository;


    public void createSendFollowUp(Long collectorId, SendFollowUp sendFollowUp){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            sendFollowUp.setCollector(collector);
            sendFollowUpRepository.save(sendFollowUp);
        }
    }

    public Iterable<SendFollowUp> getSendFollowUp(){
        return sendFollowUpRepository.findAll();
    }

    public Iterable<SendFollowUp> getSendFollowUpByCollectorId(Long collectorId){
        return sendFollowUpRepository.findSendFollowUpByCollectorId(collectorId);
    }

    public ResponseEntity deleteSendFollowUp(Long collectorId, Long id){
        SendFollowUp sendFollowUpToDelete = sendFollowUpRepository.findById(id).orElse(null);

        if (sendFollowUpToDelete != null && sendFollowUpToDelete.getCollector().getCollector_id().equals(collectorId)) {
            sendFollowUpRepository.deleteById(id);
            return new ResponseEntity<>("SendFollowUp Deleted successfully", HttpStatus.OK);
        } else if (sendFollowUpToDelete != null) {
            return new ResponseEntity<>("SendFollowUp does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("SendFollowUp not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateSendFollowUp(Long collectorId, Long id, SendFollowUp sendFollowUp){
        SendFollowUp sendFollowUpForUpdate = sendFollowUpRepository.findById(id).orElse(null);
        if (sendFollowUpForUpdate != null && sendFollowUpForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            sendFollowUpForUpdate.setPaymentStatus(sendFollowUp.getPaymentStatus());
            sendFollowUpForUpdate.setReminderMessage(sendFollowUp.getReminderMessage());
            sendFollowUpRepository.save(sendFollowUpForUpdate);
            return new ResponseEntity<>("SendFollowUp Updated successfully", HttpStatus.OK);
        } else if (sendFollowUpForUpdate != null) {
            return new ResponseEntity<>("SendFollowUp does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("SendFollowUp not found",HttpStatus.NOT_FOUND);
        }
    }
}
