package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client,Object> {
    Client findByUsername(String fullName);
    /*Optional<Client> findbyId(Long client_id);*/
}

