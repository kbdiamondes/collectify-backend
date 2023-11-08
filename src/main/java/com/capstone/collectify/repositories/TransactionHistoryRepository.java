package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("SELECT t FROM TransactionHistory t WHERE t.client.client_id = :clientId")
    List<TransactionHistory> findByClientId(@Param("clientId") Long clientId);

    /*@Query("SELECT t FROM TransactionHistory t WHERE t.client.client_id = :clientId AND t.contract.dueAmount = 0")
    List<TransactionHistory> findPaymentRecordsWithZeroAmount(@Param("clientId") Long clientId);*/
}