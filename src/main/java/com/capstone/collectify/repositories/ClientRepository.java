package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Object> {
   Optional <Client> findByUsername(String username);

   boolean existsByUsername(String username);

   boolean existsByEmail(String email);
}
