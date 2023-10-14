package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Collector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectorRepository extends CrudRepository<Collector, Object> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
