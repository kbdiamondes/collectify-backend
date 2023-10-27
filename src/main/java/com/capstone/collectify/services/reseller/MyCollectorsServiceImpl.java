package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCollectorsServiceImpl implements MyCollectorsService {

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public List<Collector> getCollectorsAssignedByReseller(Long resellerId) {
        return collectorRepository.findCollectorsAssignedByReseller(resellerId);
    }

    @Override
    public List<Contract> getContractsAssignedToCollectorByReseller(Long resellerId, Long collectorId) {
        return contractRepository.findContractsAssignedToCollectorByReseller(resellerId, collectorId);
    }
}