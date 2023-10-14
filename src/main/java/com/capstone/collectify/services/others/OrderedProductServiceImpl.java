package com.capstone.collectify.services.others;


import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.repositories.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {

    @Autowired
    private final OrderedProductRepository orderedProductRepository;

    public OrderedProductServiceImpl(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }

    public Iterable<OrderedProduct> getOrderedProduct() {
        return orderedProductRepository.findAll();
    }

}

