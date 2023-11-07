package com.capstone.collectify.repositories;

import com.capstone.collectify.models.PaymentTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransactionRepository extends CrudRepository<PaymentTransaction, Object> {
    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.contract.client.client_id = :clientId")
    List<PaymentTransaction> findPaymentTransactionsByClientId(@Param("clientId") Long clientId);
}
