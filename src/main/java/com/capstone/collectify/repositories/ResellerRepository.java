package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Reseller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResellerRepository extends CrudRepository<Reseller,Object> {
}