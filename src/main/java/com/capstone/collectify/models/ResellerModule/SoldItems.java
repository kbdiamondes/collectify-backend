package com.capstone.collectify.models.ResellerModule;

import com.capstone.collectify.models.Reseller;

import javax.persistence.*;

@Entity
@Table(name = "soldItems")
public class SoldItems {
    @Id
    @GeneratedValue
    private Long soldItems_id;

    @Column
    private String itemName;

    @Column
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name="reseller_id", nullable = false)
    private Reseller reseller;

    public SoldItems() {
    }
    public SoldItems(String itemName, int stockQuantity, Reseller reseller) {
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.reseller = reseller;
    }

    public Long getSoldItemsID() {
        return soldItems_id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }
}
