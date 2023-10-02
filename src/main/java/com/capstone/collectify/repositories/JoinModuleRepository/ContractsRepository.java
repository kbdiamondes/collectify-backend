package com.capstone.collectify.repositories.JoinModuleRepository;

import com.capstone.collectify.models.JoinModule.Contracts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContractsRepository extends CrudRepository<Contracts,Object> {

    @Query("SELECT cl.username, co.itemName, co.itemPrice, co.paymentType FROM Client cl JOIN Contracts co WHERE cl.reseller.reseller_id = :resellerId")
    Iterable<Object[]> findContractsByResellerId(@Param("resellerId") Long resellerId);


    /*@Query("SELECT co FROM Contracts c WHERE c.collector.collector_id = :collectorId")
    Iterable<Contracts> findContractsByCollector(@Param("collectorId") Long collectorId);*/



}
