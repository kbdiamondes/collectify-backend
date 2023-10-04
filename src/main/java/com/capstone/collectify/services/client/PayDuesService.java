package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.Map;

public interface PayDuesService {
    //void payDues(Long clientId, Long contractId, BigDecimal amount) throws AccessDeniedException;

    void payDues(Long clientId, Long contractId, Map<String,String> amount, String base64ImageData, String fileName, String contentType) throws AccessDeniedException, IOException;
}
