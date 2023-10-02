package com.capstone.collectify.repositories.JoinModuleRepository;

import com.capstone.collectify.models.JoinModule.Contracts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AssignedCollection extends CrudRepository<AssignedCollection,Object> {
    @Query("SELECT c FROM PaymentAssurance c WHERE c.reseller.collector_id = :collectorId")
    Iterable<Contracts> findContractsByCollectorId(@Param("collectorId") Long collectorId);
}
