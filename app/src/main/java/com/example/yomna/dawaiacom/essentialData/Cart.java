package com.example.yomna.dawaiacom.essentialData;

import android.widget.RelativeLayout;

import com.example.yomna.dawaiacom.CartItem;
import com.example.yomna.dawaiacom.Product;

import java.util.ArrayList;

/**
 * Created by yomna on 4/18/19.
 */

public class Cart {

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void addToCart(Product product){
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        items.add(cartItem);
    }



    private ArrayList<CartItem> items = new ArrayList<CartItem>();
    private static Cart instance = null;
    protected Cart(){

    }

    public static Cart getInstance(){
        if (instance == null)
            instance = new Cart();
        return instance;
    }

    public boolean isInCart(Product product){
        for(int i = 0; i < items.size(); i++){
            if (items.get(i).getProduct().getId() == product.getId())
                return true;
        }
        return false;
    }

    public void removeItem(Product product){
        for(int i = 0; i < items.size(); i++){
            if (items.get(i).getProduct().getId() == product.getId())
                items.remove(i);
        }
    }
}
