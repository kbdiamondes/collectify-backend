package com.capstone.collectify.repositories.CollectorModuleRepository;

import com.capstone.collectify.models.CollectorModules.FollowUp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends CrudRepository<FollowUp,Object> {

    @Query("SELECT fu FROM FollowUp fu WHERE fu.collector.collector_id = :collectorId")
    Iterable<FollowUp> findFollowUpByCollectorId(@Param("collectorId") Long collectorId);


}
