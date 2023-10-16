package com.capstone.collectify.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column
    private String productid;

    @Column
    private String name;
    @Column
    private String unit;
    @Column
    private double price;

    @Column
    private double commissionrate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderedProduct> orderedProducts;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCommissionrate() {
        return commissionrate;
    }

    public void setCommissionrate(double commissionrate) {
        this.commissionrate = commissionrate;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
