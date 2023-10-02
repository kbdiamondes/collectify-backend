package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.CollectAllDuePayments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectAllDuePaymentsRepository extends CrudRepository<CollectAllDuePayments,Object> {
    @Query("SELECT cadp FROM CollectAllDuePayments cadp WHERE cadp.collector.collector_id = :collectorId")
    Iterable<CollectAllDuePayments> findCollectAllDuePaymentsByCollectorId(@Param("collectorId") Long collectorId);
}
