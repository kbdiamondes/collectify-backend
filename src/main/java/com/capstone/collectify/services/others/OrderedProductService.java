package com.capstone.collectify.services.others;

import com.capstone.collectify.models.OrderedProduct;
import com.capstone.collectify.models.Product;

public interface OrderedProductService {

    Iterable<OrderedProduct> getOrderedProduct();
}
