package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Reseller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResellerRepository extends CrudRepository<Reseller, Object> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Reseller> findByUsername(String username);
}
