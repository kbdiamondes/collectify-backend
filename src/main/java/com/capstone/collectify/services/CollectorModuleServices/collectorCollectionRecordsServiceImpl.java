package com.capstone.collectify.services.CollectorModuleServices;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.collectorCollectionRecords;
import com.capstone.collectify.repositories.CollectorModuleRepository.collectorCollectionRecordsRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class collectorCollectionRecordsServiceImpl implements collectorCollectionRecordsService{
    @Autowired
    private collectorCollectionRecordsRepository collectorCollectionRecordsRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    public void createCollectorCollectionRecords(Long collectorId, collectorCollectionRecords collectorCollectionRecords){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            collectorCollectionRecords.setCollector(collector);
            collectorCollectionRecordsRepository.save(collectorCollectionRecords);
        }
    }

    public Iterable<collectorCollectionRecords> getCollectorCollectionRecords(){
        return collectorCollectionRecordsRepository.findAll();
    }

    public Iterable<collectorCollectionRecords> getCollectorCollectionRecordsByCollectorId(Long collectorId){
        return collectorCollectionRecordsRepository.findCollectorCollectionRecordsByCollectorId(collectorId);
    }

    public ResponseEntity deleteCollectorCollectionRecords(Long collectorId, Long id){
        collectorCollectionRecords collectorCollectionRecordsToDelete = collectorCollectionRecordsRepository.findById(id).orElse(null);

        if (collectorCollectionRecordsToDelete != null && collectorCollectionRecordsToDelete.getCollector().getCollector_id().equals(collectorId)) {
            collectorCollectionRecordsRepository.deleteById(id);
            return new ResponseEntity<>("collectorCollectionRecords Deleted successfully", HttpStatus.OK);
        } else if (collectorCollectionRecordsToDelete != null) {
            return new ResponseEntity<>("collectorCollectionRecords does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("collectorCollectionRecords not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateCollectorCollectionRecords(Long collectorId, Long id, collectorCollectionRecords collectorCollectionRecords){
        collectorCollectionRecords collectorCollectionRecordsForUpdate = collectorCollectionRecordsRepository.findById(id).orElse(null);
        if (collectorCollectionRecordsForUpdate != null && collectorCollectionRecordsForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            collectorCollectionRecordsForUpdate.setPayDues(collectorCollectionRecords.getPayDues());
            collectorCollectionRecordsForUpdate.setCollectPayments(collectorCollectionRecords.getCollectPayments());
            collectorCollectionRecordsRepository.save(collectorCollectionRecordsForUpdate);
            return new ResponseEntity<>("collectorCollectionRecords Updated successfully", HttpStatus.OK);
        } else if (collectorCollectionRecordsForUpdate != null) {
            return new ResponseEntity<>("collectorCollectionRecords does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("collectorCollectionRecords not found",HttpStatus.NOT_FOUND);
        }
    }
}
