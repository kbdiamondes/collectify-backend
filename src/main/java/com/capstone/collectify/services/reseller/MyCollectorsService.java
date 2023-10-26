package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Contract;

import java.util.List;

public interface MyCollectorsService {
    List<Contract> getAssignedContractsForReseller(Long resellerId);
}
