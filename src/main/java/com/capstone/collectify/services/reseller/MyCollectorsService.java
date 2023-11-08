package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;

import java.util.List;

public interface MyCollectorsService {
    List<Collector> getCollectorsAssignedByReseller(Long resellerId);
    /*
    List<Collector> getCollectorsAssignedByReseller(Long resellerId);
    List<Contract> getContractsAssignedToCollectorByReseller(Long resellerId, Long collectorId);*/
}
