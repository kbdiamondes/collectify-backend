package com.capstone.collectify.services.others;


import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.repositories.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {

    @Autowired
    private OrderedProductRepository orderedProductRepository;


    public Iterable<OrderedProduct> getOrderedProduct() {
        return orderedProductRepository.findAll();
    }

}

