package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.SendFollowUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SendFollowUpRepository extends CrudRepository<SendFollowUp,Object> {

    @Query("SELECT sfu FROM SendFollowUp sfu WHERE sfu.collector.collector_id = :collectorId")
    Iterable<SendFollowUp> findSendFollowUpByCollectorId(@Param("collectorId") Long collectorId);

}
