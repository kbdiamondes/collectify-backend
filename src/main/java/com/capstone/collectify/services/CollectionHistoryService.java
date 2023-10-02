package com.capstone.collectify.services;

import com.capstone.collectify.models.CollectionHistory;

import java.util.List;

public interface CollectionHistoryService {
    List<CollectionHistory> getCollectionHistory(Long resellerId);
}
