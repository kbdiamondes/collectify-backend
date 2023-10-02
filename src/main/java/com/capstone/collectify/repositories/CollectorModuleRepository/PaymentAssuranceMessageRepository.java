package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.PaymentAssuranceMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAssuranceMessageRepository extends CrudRepository<PaymentAssuranceMessage,Object> {
    @Query("SELECT pam FROM PaymentAssuranceMessage pam WHERE pam.collector.collector_id = :collectorId")
    Iterable<PaymentAssuranceMessage> findPaymentAssuranceMessageByCollectorId(@Param("collectorId") Long collectorId);




}
