package com.capstone.collectify.services.reseller;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;

import java.nio.file.AccessDeniedException;

public interface CollectorCollectionService {

    void assignCollector(Long resellerId, Long contractId, Long collectorId) throws AccessDeniedException;
}
