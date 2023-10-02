package com.capstone.collectify.services.CollectorModuleServices;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.FollowUp;
import com.capstone.collectify.repositories.CollectorModuleRepository.FollowUpRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowUpServiceImpl implements FollowUpService{
    @Autowired
    private FollowUpRepository followUpRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    public void createFollowUp(Long collectorId, FollowUp followUp){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            followUp.setCollector(collector);
            followUpRepository.save(followUp);
        }
    }

    public Iterable<FollowUp> getFollowUp(){
        return followUpRepository.findAll();
    }

    public Iterable<FollowUp> getFollowUpByCollectorId(Long collectorId) {
        return followUpRepository.findFollowUpByCollectorId(collectorId);
    }

    public ResponseEntity deleteFollowUp(Long collectorId, Long id){
        FollowUp followUpToDelete = followUpRepository.findById(id).orElse(null);

        if (followUpToDelete != null && followUpToDelete.getCollector().getCollector_id().equals(collectorId)) {
            followUpRepository.deleteById(id);
            return new ResponseEntity<>("FollowUp Deleted successfully", HttpStatus.OK);
        } else if (followUpToDelete != null) {
            return new ResponseEntity<>("FollowUp does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("FollowUp not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateFollowUp(Long collectorId, Long id, FollowUp followUp){
        FollowUp followUpForUpdate = followUpRepository.findById(id).orElse(null);
        if (followUpForUpdate != null && followUpForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            followUpForUpdate.setPaymentStatus(followUp.getPaymentStatus());
            followUpRepository.save(followUpForUpdate);
            return new ResponseEntity<>("FollowUp Updated successfully", HttpStatus.OK);
        } else if (followUpForUpdate != null) {
            return new ResponseEntity<>("FollowUp does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("FollowUp not found",HttpStatus.NOT_FOUND);
        }
    }
}
