package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface ResellerService{
    Contract createContract(Long resellerId, Long clientId, BigDecimal dueAmount);
    void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException;
    void collectPayment(Long resellerId, Long contractId, BigDecimal amount) throws AccessDeniedException;
    List<CollectionHistory> getCollectionHistory(Long resellerId);

    Reseller createReseller(Reseller reseller);

    Optional<Reseller> getResellerById(Long id);
}