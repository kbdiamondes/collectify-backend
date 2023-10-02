package com.capstone.collectify.services.CollectorModuleServices;


import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.CollectorModules.PaymentAssuranceMessage;
import com.capstone.collectify.repositories.CollectorModuleRepository.PaymentAssuranceMessageRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentAssuranceMessageServiceImpl implements PaymentAssuranceMessageService{
    @Autowired
    private PaymentAssuranceMessageRepository paymentAssuranceMessageRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    public void createPaymentAssuranceMessage(Long collectorId, PaymentAssuranceMessage paymentAssuranceMessage){
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            paymentAssuranceMessage.setCollector(collector);
            paymentAssuranceMessageRepository.save(paymentAssuranceMessage);
        }
    }

    public Iterable<PaymentAssuranceMessage> getPaymentAssuranceMessage(){
        return paymentAssuranceMessageRepository.findAll();
    }

    public Iterable<PaymentAssuranceMessage> getPaymentAssuranceMessageByCollectorId(Long collectorId){
        return paymentAssuranceMessageRepository.findPaymentAssuranceMessageByCollectorId(collectorId);
    }

    public ResponseEntity deletePaymentAssuranceMessage(Long collectorId, Long id){
        PaymentAssuranceMessage paymentAssuranceMessageToDelete = paymentAssuranceMessageRepository.findById(id).orElse(null);

        if (paymentAssuranceMessageToDelete != null && paymentAssuranceMessageToDelete.getCollector().getCollector_id().equals(collectorId)) {
            paymentAssuranceMessageRepository.deleteById(id);
            return new ResponseEntity<>("PaymentAssuranceMessage Deleted successfully", HttpStatus.OK);
        } else if (paymentAssuranceMessageToDelete != null) {
            return new ResponseEntity<>("PaymentAssuranceMessage does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("PaymentAssuranceMessage not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updatePaymentAssuranceMessage(Long collectorId, Long id, PaymentAssuranceMessage paymentAssuranceMessage){
        PaymentAssuranceMessage paymentAssuranceMessageForUpdate = paymentAssuranceMessageRepository.findById(id).orElse(null);
        if (paymentAssuranceMessageForUpdate != null && paymentAssuranceMessageForUpdate.getCollector().getCollector_id().equals(collectorId)) {
            paymentAssuranceMessageForUpdate.setCollectorMessage(paymentAssuranceMessage.getCollectorMessage());
            paymentAssuranceMessageForUpdate.setChatCollector(paymentAssuranceMessage.getChatCollector());
            paymentAssuranceMessageRepository.save(paymentAssuranceMessageForUpdate);
            return new ResponseEntity<>("PaymentAssuranceMessage Updated successfully", HttpStatus.OK);
        } else if (paymentAssuranceMessageForUpdate != null) {
            return new ResponseEntity<>("PaymentAssuranceMessage does not belong to the specified collector", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("PaymentAssuranceMessage not found",HttpStatus.NOT_FOUND);
        }
    }
}
