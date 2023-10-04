package com.capstone.collectify.services.client;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;

public interface PayDuesService {
    void payDues(Long clientId, Long contractId, BigDecimal amount) throws AccessDeniedException;
}
