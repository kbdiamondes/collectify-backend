package com.capstone.collectify.repositories;

import com.capstone.collectify.models.CollectionHistory;
import com.capstone.collectify.models.Reseller;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionHistoryRepository extends CrudRepository<CollectionHistory, Object> {

    List<CollectionHistory> findByReseller(Reseller reseller);
}
