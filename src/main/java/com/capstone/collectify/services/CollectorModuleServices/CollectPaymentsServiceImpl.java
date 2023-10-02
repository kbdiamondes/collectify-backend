package com.capstone.collectify.services.CollectorModuleServices;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.CollectPayments;
import com.capstone.collectify.repositories.CollectorModuleRepository.CollectPaymentsRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class CollectPaymentsServiceImpl implements CollectPaymentsService{
    @Autowired
    private CollectPaymentsRepository collectPaymentsRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    public void createCollectPayments(Long collectorId, CollectPayments collectPayments){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            collectPayments.setCollector(collector);
            collectPaymentsRepository.save(collectPayments);
        }
    }

    public Iterable<CollectPayments> getCollectPayments(){
        return collectPaymentsRepository.findAll();
    }

    public Iterable<CollectPayments> getCollectPaymentsByCollectorId(Long collectorId){
        return collectPaymentsRepository.findCollectPaymentsByCollectorId(collectorId);
    }

    public ResponseEntity deleteCollectPayments(Long collectorId, Long id){
        CollectPayments collectPaymentsToDelete = collectPaymentsRepository.findById(id).orElse(null);

        if (collectPaymentsToDelete != null && collectPaymentsToDelete.getCollector().getCollector_id().equals(collectorId)) {
            collectPaymentsRepository.deleteById(id);
            return new ResponseEntity<>("CollectPayments Deleted successfully", HttpStatus.OK);
        } else if (collectPaymentsToDelete != null) {
            return new ResponseEntity<>("CollectPayments does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("CollectPayments not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateCollectPayments(Long collectorId, Long id, CollectPayments collectPayments){
        CollectPayments collectPaymentsForUpdate = collectPaymentsRepository.findById(id).orElse(null);
        if (collectPaymentsForUpdate != null && collectPaymentsForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            collectPaymentsForUpdate.setCollectionDate(collectPayments.getCollectionDate());
            collectPaymentsForUpdate.setRequiredCollectibles(collectPayments.getRequiredCollectibles());
            collectPaymentsForUpdate.setItemCollectible(collectPayments.getItemCollectible());
            collectPaymentsForUpdate.setPaymentType(collectPayments.getPaymentType());
            collectPaymentsForUpdate.setTransactionProof(collectPayments.getTransactionProof());
            collectPaymentsRepository.save(collectPaymentsForUpdate);
            return new ResponseEntity<>("CollectPayments Updated successfully", HttpStatus.OK);
        } else if (collectPaymentsForUpdate != null) {
            return new ResponseEntity<>("CollectPayments does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("CollectPayments not found",HttpStatus.NOT_FOUND);
        }
    }
}
