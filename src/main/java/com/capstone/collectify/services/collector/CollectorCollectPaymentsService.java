package com.capstone.collectify.services.collector;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public interface CollectorCollectPaymentsService {

    void collectPayments(Long collectorId, Long paymentTransactionId, String paymentType, String base64ImageData, String fileName, String contentType)
            throws AccessDeniedException, IOException;
    void collectPaymentsFromAllTransactions(Long collectorId, String paymentType, String base64ImageData, String fileName, String contentType)
            throws AccessDeniedException, IOException;
    /*
    void collectPayments(Long collectorId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType ) throws AccessDeniedException, IOException;
    void collectPaymentsFromAllContracts(Long collectorId, String paymentType, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException;

    */

}
