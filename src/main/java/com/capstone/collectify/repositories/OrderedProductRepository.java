package com.capstone.collectify.repositories;

import com.capstone.collectify.models.OrderedProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends CrudRepository<OrderedProduct, Object> {
}
