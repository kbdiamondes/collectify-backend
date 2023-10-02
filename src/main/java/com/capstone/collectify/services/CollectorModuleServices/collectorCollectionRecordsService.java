package com.capstone.collectify.services.CollectorModuleServices;

import com.capstone.collectify.models.CollectorModules.collectorCollectionRecords;
import org.springframework.http.ResponseEntity;

public interface collectorCollectionRecordsService {
    void createCollectorCollectionRecords(Long collectorId, collectorCollectionRecords paymentDues);

    Iterable<collectorCollectionRecords> getCollectorCollectionRecords();

    Iterable<collectorCollectionRecords> getCollectorCollectionRecordsByCollectorId(Long collectorId);

    ResponseEntity deleteCollectorCollectionRecords(Long collectorId, Long id);

    ResponseEntity updateCollectorCollectionRecords(Long collectorId, Long id, collectorCollectionRecords paymentDues);
}
