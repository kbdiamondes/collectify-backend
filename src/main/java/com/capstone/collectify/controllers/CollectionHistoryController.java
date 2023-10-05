package com.capstone.collectify.controllers;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.services.CollectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/collection-history")
public class CollectionHistoryController {

    @Autowired
    private CollectionHistoryService collectionHistoryService;

    @GetMapping("/reseller/{resellerId}")
    public List<CollectionHistory> getCollectionHistoryByReseller(@PathVariable Long resellerId) {
        return collectionHistoryService.getCollectionHistory(resellerId);
    }

    // Add other endpoints for CollectionHistory-related operations
}
