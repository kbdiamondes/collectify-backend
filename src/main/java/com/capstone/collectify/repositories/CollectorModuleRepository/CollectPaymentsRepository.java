package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.CollectPayments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectPaymentsRepository extends CrudRepository<CollectPayments,Object> {
    @Query("SELECT cp FROM CollectPayments cp WHERE cp.collector.collector_id = :collectorId")
    Iterable<CollectPayments> findCollectPaymentsByCollectorId(@Param("collectorId") Long collectorId);
}
