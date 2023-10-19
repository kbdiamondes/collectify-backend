package com.capstone.collectify.repositories;

import com.capstone.collectify.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Object> {
}
