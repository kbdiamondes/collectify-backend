package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface ResellerService{
    Contract createContract(Long resellerId, String clientUsername,  String username, String itemName, Long fullPrice, Boolean isPaid, int installmentDuration, boolean isMonthly);
    void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException;
    void collectPayment(Long resellerId, Long contractId, BigDecimal amount) throws AccessDeniedException;
    List<CollectionHistory> getCollectionHistory(Long resellerId);

    Reseller createReseller(Reseller reseller);

    Optional<Reseller> getResellerById(Long id);

    Iterable<Reseller> getReseller();

    Collector getAssignedCollector(Long resellerId, Long contractId);
    int countActiveUnpaidContractsForReseller(Long resellerId);

}
