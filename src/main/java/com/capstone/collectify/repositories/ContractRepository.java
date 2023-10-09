package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Contract;
import com.capstone.collectify.models.Reseller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Object> {

    List<Contract> findByClient(Client client);

    List<Contract> findByReseller(Reseller reseller);

    List<Contract> findByIsMonthly(boolean isMonthly);
}
