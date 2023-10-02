package com.capstone.collectify.repositories.ClientModuleRepository;

import com.capstone.collectify.models.ClientModules.DuePayments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing DuePayments entities.
 */
@Repository
public interface DuePaymentsRepository extends CrudRepository<DuePayments, Object> {

    /**
     * Retrieves a list of DuePayments associated with a specific client.
     *
     * @param clientId The ID of the client.
     * @return An iterable of DuePayments associated with the client.
     */
    @Query("SELECT dp FROM DuePayments dp WHERE dp.client.client_id = :clientId")
    Iterable<DuePayments> findDuePaymentsByClientId(@Param("clientId") Long clientId);
}