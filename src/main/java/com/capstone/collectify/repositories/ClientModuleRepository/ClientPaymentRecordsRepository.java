package com.capstone.collectify.repositories.ClientModuleRepository;

import com.capstone.collectify.models.ClientModules.ClientPaymentRecords;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPaymentRecordsRepository extends CrudRepository<ClientPaymentRecords,Object> {


    @Query("SELECT cpr FROM ClientPaymentRecords cpr WHERE cpr.client.client_id = :clientId")
    Iterable<ClientPaymentRecords> findClientPaymentRecordsClientId(@Param("clientId") Long clientId);
}
