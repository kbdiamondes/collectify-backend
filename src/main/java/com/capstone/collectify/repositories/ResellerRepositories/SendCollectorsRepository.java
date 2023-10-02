package com.capstone.collectify.repositories.ResellerRepositories;

import com.capstone.collectify.models.ResellerModule.SendCollectors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SendCollectorsRepository extends CrudRepository<SendCollectors,Object> {
    @Query("SELECT sc FROM SendCollectors sc WHERE sc.reseller.reseller_id = :resellerId")
    Iterable<SendCollectors> findSendCollectorsByResellerId(@Param("resellerId") Long resellerId);
}
