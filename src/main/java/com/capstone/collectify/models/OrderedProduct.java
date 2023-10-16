package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_productid;

    @Column
    private String orderedproductid;

    @Column
    private int quantity;

    @Column
    private double subtotal;

    @ManyToOne
    @JoinColumn(name="product_productid")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Contract contract;

    public Long getOrder_productid() {
        return order_productid;
    }

    public void setOrder_productid(Long order_productid) {
        this.order_productid = order_productid;
    }

    public String getOrderedproductid() {
        return orderedproductid;
    }

    public void setOrderedproductid(String orderedproductid) {
        this.orderedproductid = orderedproductid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}

