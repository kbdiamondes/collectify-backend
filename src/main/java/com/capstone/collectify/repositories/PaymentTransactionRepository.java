package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import com.capstone.collectify.models.PaymentTransaction;
import com.capstone.collectify.models.PaymentTransactionWithClientAndItemDTO;
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

    List<PaymentTransaction> findByResellerAndCollectorIsNullAndIsPaidIsTrueAndIsCollectedIsFalse(Reseller reseller);

    List<PaymentTransaction> findByCollectorAndIsPaidAndIsCollected(Collector collector, boolean isPaid, boolean isCollected);

    @Query("SELECT pt, c.itemName FROM PaymentTransaction pt JOIN pt.contract c JOIN c.client client WHERE pt.isPaid = false AND client.client_id = :clientId")
    List<Object[]> findUnpaidPaymentTransactionsWithItemNames(@Param("clientId") Long clientId);

    @Query("SELECT pt, c.itemName, c.client.fullName FROM PaymentTransaction pt JOIN pt.contract c JOIN c.reseller r WHERE r.reseller_id = :resellerId")
    List<Object[]> findPaymentTransactionsWithItemNamesByResellerId(@Param("resellerId") Long resellerId);

    @Query("SELECT pt, c.itemName, cl.fullName FROM PaymentTransaction pt JOIN pt.contract c JOIN c.client cl WHERE pt.isPaid = false AND c.reseller.reseller_id = :resellerId")
    List<Object[]> findUnpaidPaymentTransactionsWithNamesAndClientByResellerId(@Param("resellerId") Long resellerId);

    @Query("SELECT pt, pt.contract.client.fullName, pt.contract.itemName FROM PaymentTransaction pt WHERE pt.contract.reseller.reseller_id = :resellerId AND pt.isCollected = false")
    List<Object[]> findUncollectedPaymentTransactionsWithNamesAndClientByResellerId(@Param("resellerId") Long resellerId);

    @Query("SELECT pt, pt.contract.client.fullName, pt.contract.itemName FROM PaymentTransaction pt WHERE pt.contract.reseller.reseller_id = :resellerId AND pt.isCollected = false AND pt.collector IS NULL")
    List<Object[]> findUncollectedAndUnassignedPaymentTransactionsWithNamesAndClientByResellerId(@Param("resellerId") Long resellerId);




}
