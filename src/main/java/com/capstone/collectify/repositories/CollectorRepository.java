package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Object> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);

    @Query("SELECT pt FROM Collector c JOIN c.assignedPaymentTransactions pt " +
            "WHERE c.collector_id = :collectorId AND pt.isCollected = false")
    List<PaymentTransaction> findUncollectedPaymentTransactionsByCollectorId(@Param("collectorId") Long collectorId);
    /*
    @Query("SELECT c FROM Collector c JOIN c.assignedContract ac WHERE ac.reseller.reseller_id = :resellerId")
    List<Collector> findCollectorsAssignedByReseller(@Param("resellerId") Long resellerId);
*/
}
