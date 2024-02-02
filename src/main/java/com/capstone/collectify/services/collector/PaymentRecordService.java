package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.PaymentTransactionForCollectionListDTO;

import java.util.List;

public interface PaymentRecordService {
    List<PaymentTransactionForCollectionListDTO> getCollectedPaymentsForCollector(Long collectorId);

   // List<Contract> getPaymentRecordsForCollector(Long collectorId);
}
