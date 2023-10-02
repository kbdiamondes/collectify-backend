package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Reseller;
import com.capstone.collectify.repositories.CollectionHistoryRepository;
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



    @Override
    public List<CollectionHistory> getCollectionHistory(Long resellerId) {
        Reseller reseller = resellerRepository.findById(resellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reseller not found with id: " + resellerId));

        return collectionHistoryRepository.findByReseller(reseller);
    }

    public Iterable<CollectionHistory> getCollectionHistory() {
        return collectionHistoryRepository.findAll();
    }
}
