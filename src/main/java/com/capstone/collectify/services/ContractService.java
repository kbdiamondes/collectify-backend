package com.capstone.collectify.services;

import com.capstone.collectify.models.Contract;

import java.math.BigDecimal;
import java.util.List;

public interface ContractService {
    Contract createContract(Contract contract);
    Contract getContractById(Long id);
    List<Contract> getClientContracts(Long clientId);
    List<Contract> getResellerContracts(Long resellerId);
    void assignCollectorToContract(Long contractId, Long collectorId);
    void collectPayment(Long contractId, BigDecimal amount);

    Iterable<Contract> getContract();
}

