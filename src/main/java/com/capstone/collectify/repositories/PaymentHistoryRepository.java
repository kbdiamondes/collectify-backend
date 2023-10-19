package com.capstone.collectify.repositories;

import com.capstone.collectify.models.FileDB;
import com.capstone.collectify.models.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
}
