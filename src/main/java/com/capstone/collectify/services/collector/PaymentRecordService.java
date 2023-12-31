package com.capstone.collectify.services.collector;

import com.capstone.collectify.models.Contract;

import java.util.List;

public interface PaymentRecordService {
    List<Contract> getPaymentRecordsForCollector(Long collectorId);
}
