package com.capstone.collectify.services.CollectorModuleServices;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.PaymentAssurance;
import com.capstone.collectify.repositories.CollectorModuleRepository.PaymentAssuranceRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentAssuranceServiceImpl implements PaymentAssuranceService {
    @Autowired
    private PaymentAssuranceRepository paymentAssuranceRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    public void createPaymentAssurance(Long collectorId, PaymentAssurance paymentAssurance){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            paymentAssurance.setCollector(collector);
            paymentAssuranceRepository.save(paymentAssurance);
        }
    }
    public Iterable<PaymentAssurance> getPaymentAssurance(){
        return paymentAssuranceRepository.findAll();
    }

    public Iterable<PaymentAssurance> getPaymentAssuranceByCollectorId(Long collectorId) {
        return paymentAssuranceRepository.findPaymentAssuranceByCollectorId(collectorId);
    }

    public ResponseEntity deletePaymentAssurance(Long collectorId, Long id){
        PaymentAssurance paymentAssuranceToDelete = paymentAssuranceRepository.findById(id).orElse(null);

        if (paymentAssuranceToDelete != null && paymentAssuranceToDelete.getCollector().getCollector_id().equals(collectorId)) {
            paymentAssuranceRepository.deleteById(id);
            return new ResponseEntity<>("PaymentAssurance Deleted successfully", HttpStatus.OK);
        } else if (paymentAssuranceToDelete != null) {
            return new ResponseEntity<>("PaymentAssurance does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("PaymentAssurance not found", HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity updatePaymentAssurance(Long collectorId, Long id, PaymentAssurance paymentAssurance){
        PaymentAssurance paymentAssuranceForUpdate = paymentAssuranceRepository.findById(id).orElse(null);
        if (paymentAssuranceForUpdate != null && paymentAssuranceForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            paymentAssuranceForUpdate.setPaymentStatus(paymentAssurance.getPaymentStatus());
            paymentAssuranceRepository.save(paymentAssuranceForUpdate);
            return new ResponseEntity<>("PaymentAssurance Updated successfully", HttpStatus.OK);
        } else if (paymentAssuranceForUpdate != null) {
            return new ResponseEntity<>("PaymentAssurance does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("PaymentAssurance not found",HttpStatus.NOT_FOUND);
        }
    }
}
