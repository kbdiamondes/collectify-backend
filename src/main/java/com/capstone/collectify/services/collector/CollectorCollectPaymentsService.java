package com.capstone.collectify.services.collector;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public interface CollectorCollectPaymentsService {
    void collectPayments(Long collectorId, Long contractId, String paymentType, String base64ImageData, String fileName, String contentType ) throws AccessDeniedException, IOException;
}
