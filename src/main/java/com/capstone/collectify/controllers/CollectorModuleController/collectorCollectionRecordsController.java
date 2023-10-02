package com.capstone.collectify.controllers.CollectorModuleController;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.collectorCollectionRecords;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.services.CollectorModuleServices.collectorCollectionRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class collectorCollectionRecordsController {
    @Autowired
    collectorCollectionRecordsService collectorCollectionRecordsService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/collectorCollectionRecords/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createCollectorCollectionRecords(@PathVariable Long collectorId, @RequestBody collectorCollectionRecords collectorCollectionRecords) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            collectorCollectionRecordsService.createCollectorCollectionRecords(collectorId, collectorCollectionRecords);
            return new ResponseEntity<>("collectorCollectionRecords created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/collectorCollectionRecords", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectorCollectionRecords() {
        return new ResponseEntity<>(collectorCollectionRecordsService.getCollectorCollectionRecords(), HttpStatus.OK);
    }

    @RequestMapping(value="/collectorCollectionRecords/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectorCollectionRecordsByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(collectorCollectionRecordsService.getCollectorCollectionRecordsByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/collectorCollectionRecords/{collectorId}/{collectorCollectionRecordsId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCollectorCollectionRecords(@PathVariable Long collectorId, @PathVariable Long collectorCollectionRecordsId) {
        return collectorCollectionRecordsService.deleteCollectorCollectionRecords(collectorId, collectorCollectionRecordsId);
    }

    @RequestMapping(value="/collectorCollectionRecords/{collectorId}/{collectorCollectionRecordsId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollectorCollectionRecords(@PathVariable Long collectorId, @PathVariable Long collectorCollectionRecordsId, @RequestBody collectorCollectionRecords collectorCollectionRecords) {
        return collectorCollectionRecordsService.updateCollectorCollectionRecords(collectorId, collectorCollectionRecordsId, collectorCollectionRecords);
    }
}