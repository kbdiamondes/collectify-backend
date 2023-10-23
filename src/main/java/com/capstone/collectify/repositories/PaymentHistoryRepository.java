package com.capstone.collectify.repositories;

import com.capstone.collectify.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
