package com.example.yomna.dawaiacom;

/**
 * Created by yomna on 4/18/19.
 */

public class CartItem {
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int quantity;
}
