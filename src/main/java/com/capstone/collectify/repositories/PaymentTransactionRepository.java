package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.Reseller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransactionRepository extends CrudRepository<PaymentTransaction, Object> {
    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.contract.client.client_id = :clientId")
    List<PaymentTransaction> findPaymentTransactionsByClientId(@Param("clientId") Long clientId);


    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.contract.client.client_id = :clientId AND pt.isPaid = false")
    List<PaymentTransaction> findUnpaidPaymentTransactionsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.contract.reseller.reseller_id = :resellerId AND pt.isPaid = false")
    List<PaymentTransaction> findUnpaidPaymentTransactionsByResellerId(@Param("resellerId") Long resellerId);

    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.reseller.reseller_id = :resellerId")
    List<PaymentTransaction> findByResellerId(@Param("resellerId") Long resellerId);

    List<PaymentTransaction> findByResellerAndCollectorIsNullAndIsPaidIsTrueAndIsCollectedIsFalse(Reseller reseller);

    List<PaymentTransaction> findByCollectorAndIsPaidAndIsCollected(Collector collector, boolean isPaid, boolean isCollected);
}
