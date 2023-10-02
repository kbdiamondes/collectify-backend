package com.capstone.collectify.repositories.ClientModuleRepository;

import com.capstone.collectify.models.ClientModules.PayDues;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayDuesRepository extends CrudRepository<PayDues,Object> {
    @Query("SELECT pd FROM PayDues pd WHERE pd.client.client_id = :clientId")
    Iterable<PayDues> findPayDuesByClientId(@Param("clientId") Long clientId);
}
