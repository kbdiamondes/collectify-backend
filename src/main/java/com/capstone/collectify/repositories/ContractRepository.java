package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Object> {

    List<Contract> findByClient(Client client);

    List<Contract> findByReseller(Reseller reseller);

    List<Contract> findByIsMonthly(boolean isMonthly);

    boolean existsByOrderid(String orderid);

    List<Contract> findByIsPaidFalse();

    List<Contract> findByClientAndIsPaid(Client client, boolean b);
    @Query("SELECT c FROM Contract c " +
            "WHERE c.collector.collector_id = :collectorId " +
            "AND c.dueAmount = :dueAmount")
    List<Contract> findContractsForCollectorWithDueAmount(
            @Param("collectorId") Long collectorId,
            @Param("dueAmount") BigDecimal dueAmount
    );

    @Query("SELECT c FROM Contract c WHERE c.reseller.id = :resellerId AND c.collector IS NULL")
    List<Contract> findContractsForResellerWithNoCollector(@Param("resellerId") Long resellerId);
    @Query("SELECT c FROM Contract c WHERE c.reseller.reseller_id = :resellerId AND c.collector IS NULL AND c.isPaid = false")
    List<Contract> findUnpaidContractsForReseller(@Param("resellerId") Long resellerId);

    @Query("SELECT c FROM Contract c WHERE c.reseller.reseller_id = :resellerId")
    List<Contract> findAssignedContractsForReseller(@Param("resellerId") Long resellerId);

    @Query("SELECT con FROM Contract con WHERE con.collector.collector_id = :collectorId AND con.reseller.reseller_id = :resellerId")
    List<Contract> findContractsAssignedToCollectorByReseller(@Param("resellerId") Long resellerId, @Param("collectorId") Long collectorId);

    @Query("SELECT COUNT(c) FROM Contract c WHERE c.reseller.id = :resellerId AND c.isCollected = false AND c.isPaid = false")
    int countActiveUnpaidContractsForReseller(@Param("resellerId") Long resellerId);
}
