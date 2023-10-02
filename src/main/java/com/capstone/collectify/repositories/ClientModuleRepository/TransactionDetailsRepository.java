package com.capstone.collectify.repositories.ClientModuleRepository;

import com.capstone.collectify.models.ClientModules.TransactionDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends CrudRepository<TransactionDetails,Object> {

    @Query("SELECT td FROM TransactionDetails td WHERE td.client.client_id = :clientId")
    Iterable<TransactionDetails> findTransactionDetailsByClientId(@Param("clientId") Long clientId);

}
