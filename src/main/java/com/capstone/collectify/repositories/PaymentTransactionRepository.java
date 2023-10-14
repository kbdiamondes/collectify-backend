package com.capstone.collectify.repositories;

import com.capstone.collectify.models.PaymentTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionRepository extends CrudRepository<PaymentTransaction, Object> {
}
