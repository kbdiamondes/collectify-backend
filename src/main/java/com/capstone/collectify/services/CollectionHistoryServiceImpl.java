package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
import com.capstone.collectify.repositories.CollectorRepository;
import com.capstone.collectify.repositories.ResellerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionHistoryServiceImpl implements CollectionHistoryService {

    @Autowired
    private CollectionHistoryRepository collectionHistoryRepository;

    @Autowired
    private ResellerRepository resellerRepository;

    @Autowired
    private CollectorRepository collectorRepository;


    @Override
    public List<CollectionHistory> getCollectionHistory(Long resellerId) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        return collectionHistoryRepository.findByReseller(reseller);
    }

    @Override
    public List<CollectionHistory> getCollectorCollectionHistory(Long collectorId) {
        Collector collector = collectorRepository.findById(collectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Collector not found with id: " + collectorId));

        return collectionHistoryRepository.findByCollector(collector);
    }

    public Iterable<CollectionHistory> getCollectionHistory() {
        return collectionHistoryRepository.findAll();
    }
}
