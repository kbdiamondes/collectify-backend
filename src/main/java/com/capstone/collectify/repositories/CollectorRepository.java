package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectorRepository extends CrudRepository<Collector, Object> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);

    @Query("SELECT c FROM Collector c JOIN c.assignedContract ac WHERE ac.reseller.reseller_id = :resellerId")
    List<Collector> findCollectorsAssignedByReseller(@Param("resellerId") Long resellerId);

}
