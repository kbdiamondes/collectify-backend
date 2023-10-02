package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.PaymentAssurance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAssuranceRepository extends CrudRepository<PaymentAssurance,Object> {
    @Query("SELECT pa FROM PaymentAssurance pa WHERE pa.collector.collector_id = :collectorId")
    Iterable<PaymentAssurance> findPaymentAssuranceByCollectorId(@Param("collectorId") Long collectorId);
}
