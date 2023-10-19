package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectorRepository extends CrudRepository<Collector, Object> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Object> findByUsername(String username);
}
