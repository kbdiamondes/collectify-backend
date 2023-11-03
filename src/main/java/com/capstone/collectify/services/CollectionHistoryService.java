package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;

import java.util.List;

public interface CollectionHistoryService {

    Iterable<CollectionHistory> getCollectionHistory();
    List<CollectionHistory> getCollectionHistory(Long resellerId);

    List<CollectionHistory> getCollectorCollectionHistory(Long collectorId);
}
