package com.capstone.collectify.repositories.ClientModuleRepository;

import com.capstone.collectify.models.ClientModules.PaymentMethodSelection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodSelectionRepository extends CrudRepository<PaymentMethodSelection,Object> {

    @Query("SELECT pms FROM PaymentMethodSelection pms WHERE pms.client.client_id = :clientId")
    Iterable<PaymentMethodSelection> findPaymentMethodSelectionByClientId(@Param("clientId") Long clientId);
}
