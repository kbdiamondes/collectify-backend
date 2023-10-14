package com.capstone.collectify.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orderedproduct")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderedproductid;

    @Column
    private int quantity;

    @Column
    private double subtotal;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Contract contract;


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

