package com.capstone.collectify.services.others;


import com.capstone.collectify.models.Client;
import com.capstone.collectify.models.Product;
import com.capstone.collectify.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> getProducts() {
            return productRepository.findAll();
    }
}

