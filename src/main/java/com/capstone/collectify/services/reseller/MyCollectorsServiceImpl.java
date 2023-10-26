package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Contract;
import com.capstone.collectify.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCollectorsServiceImpl implements MyCollectorsService {

    private final ContractRepository contractRepository;

    @Autowired
    public MyCollectorsServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public List<Contract> getAssignedContractsForReseller(Long resellerId) {
        return contractRepository.findAssignedContractsForReseller(resellerId);
    }
}