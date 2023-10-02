package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.SendFollowUp;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.SendFollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class SendFollowUpController {
    @Autowired
    SendFollowUpService sendFollowUpService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/sendFollowUp/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createSendFollowUp(@PathVariable Long collectorId, @RequestBody SendFollowUp sendFollowUp) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            sendFollowUpService.createSendFollowUp(collectorId, sendFollowUp);
            return new ResponseEntity<>("Send Follow Up created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/sendFollowUp", method = RequestMethod.GET)
    public ResponseEntity<Object> getSendFollowUp() {
        return new ResponseEntity<>(sendFollowUpService.getSendFollowUp(), HttpStatus.OK);
    }

    @RequestMapping(value="/sendFollowUp/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getSendFollowUpByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(sendFollowUpService.getSendFollowUpByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendFollowUp/{collectorId}/{sendFollowUpId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleSendFollowUp(@PathVariable Long collectorId, @PathVariable Long sendFollowUpId) {
        return sendFollowUpService.deleteSendFollowUp(collectorId, sendFollowUpId);
    }

    @RequestMapping(value="/sendFollowUp/{collectorId}/{sendFollowUpId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateSendFollowUp(@PathVariable Long collectorId, @PathVariable Long sendFollowUpId, @RequestBody SendFollowUp sendFollowUp) {
        return sendFollowUpService.updateSendFollowUp(collectorId, sendFollowUpId, sendFollowUp);
    }
}
