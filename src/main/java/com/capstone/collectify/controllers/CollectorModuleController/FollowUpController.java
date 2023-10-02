package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.FollowUp;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class FollowUpController {
    @Autowired
    FollowUpService followUpService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/followUp/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createFollowUp(@PathVariable Long collectorId, @RequestBody FollowUp followUp) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            followUpService.createFollowUp(collectorId, followUp);
            return new ResponseEntity<>("FollowUp created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/followUp", method = RequestMethod.GET)
    public ResponseEntity<Object> getFollowUp() {
        return new ResponseEntity<>(followUpService.getFollowUp(), HttpStatus.OK);
    }

    @RequestMapping(value="/followUp/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFollowUpByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(followUpService.getFollowUpByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/followUp/{collectorId}/{followUpId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteFollowUp(@PathVariable Long collectorId, @PathVariable Long followUpId) {
        return followUpService.deleteFollowUp(collectorId, followUpId);
    }

    @RequestMapping(value="/followUp/{collectorId}/{followUpId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateFollowUp(@PathVariable Long collectorId, @PathVariable Long followUpId, @RequestBody FollowUp followUp) {
        return followUpService.updateFollowUp(collectorId, followUpId, followUp);
    }
}