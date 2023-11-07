package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public interface CollectPaymentsService {
    void collectPayments(Long resellerId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType)
            throws AccessDeniedException, IOException;
/*
    void collectPayments(Long resellerId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType ) throws AccessDeniedException, IOException;

    void collectPaymentsFromAllContracts(Long resellerId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException;

 */
}
