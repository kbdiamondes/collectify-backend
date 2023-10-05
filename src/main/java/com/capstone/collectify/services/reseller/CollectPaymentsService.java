package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;

import java.nio.file.AccessDeniedException;

public interface CollectPaymentsService {

    void collectPayments(Long resellerId, Long contractId, String paymentType ) throws AccessDeniedException;
}
