package com.example.yomna.dawaiacom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yomna.dawaiacom.essentialData.Cart;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    Cart cart;
    ArrayList<CartItem> cartItems;
    TextView priceTv;
    double totalPrice = 0;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        priceTv = view.findViewById(R.id.total_value);
        priceTv.setText("0");
        cart = Cart.getInstance();
        cartItems = cart.getItems();
        displayCartItems();

        return view;
    }

    void setIncrementButtonListener(final View cartItem, final int index){
        TextView incrementButton = cartItem.findViewById(R.id.increment_btn);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTV = cartItem.findViewById(R.id.quantity_value);
                int quantity = cartItems.get(index).getQuantity();
                cartItems.get(index).setQuantity(quantity+1);
                quantity++;
                quantityTV.setText(quantity + "");
                totalPrice += cartItems.get(index).getProduct().getCurrentPrice();
                priceTv.setText(totalPrice + "");
            }
        });
    }

    void setDecrementButtonListener(final View cartItem, final int index){
        TextView decrementButton = cartItem.findViewById(R.id.decrement_btn);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView quantityTV = cartItem.findViewById(R.id.quantity_value);
                int quantity = cartItems.get(index).getQuantity();
                totalPrice = totalPrice - cartItems.get(index).getProduct().getCurrentPrice();
                priceTv.setText(totalPrice + "");
                if (quantity > 0) {
                    quantity--;
                    cartItems.get(index).setQuantity(quantity);
                }
                if (quantity == 0){
                    deleteItem(cartItem, index);
                }
                quantityTV.setText(quantity + "");
            }
        });
    }

    void deleteItem(final View cartItem, int index){
        totalPrice = totalPrice - cartItems.get(index).getProduct().getCurrentPrice();
        cartItems.remove(index);
        RelativeLayout cartItemsContainer = getActivity().findViewById(R.id.items_count_container);
        if (cartItems.size() > 0)
            cartItemsContainer.setVisibility(View.VISIBLE);
        else
            cartItemsContainer.setVisibility(View.INVISIBLE);
        TextView itemsCount = getActivity().findViewById(R.id.items_count_value);
        itemsCount.setText(cart.getItems().size() + "");
        displayCartItems();
    }

    View getCartItem(CartItem item){
        View cartItem = getView().inflate(getContext(), R.layout.cart_item, null);
        TextView productName = cartItem.findViewById(R.id.cart_product_name);
        TextView price = cartItem.findViewById(R.id.price_tv);
        TextView quantity = cartItem.findViewById(R.id.quantity_value);
        ImageView imageView = cartItem.findViewById(R.id.cart_product_img);

        productName.setText(item.getProduct().getName());
        price.setText(item.getProduct().getCurrentPrice() + "");
        quantity.setText(item.getQuantity() + "");
        imageView.setImageResource(item.getProduct().getImage());
        return cartItem;
    }

    void displayCartItems(){
        LinearLayout root = view.findViewById(R.id.cart_item_container);
        totalPrice = 0;
        root.removeAllViews();
        if (cartItems.size() == 0){

        }
        for  (int i = 0; i < cartItems.size(); i++){
            View cartItem = getCartItem(cartItems.get(i));
            totalPrice += cartItems.get(i).getProduct().getCurrentPrice();
            setIncrementButtonListener(cartItem, i);
            setDecrementButtonListener(cartItem, i);
            setDeleteButtonListener(cartItem, i);
            root.addView(cartItem);
        }

        if(cartItems.size() == 0){
            View emptyCart = getView().inflate(getContext(), R.layout.empty_cart, null);
            root.addView(emptyCart);
            RelativeLayout continueShopping = emptyCart.findViewById(R.id.continue_shopping_tv);
            continueShopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setFragment(new OffersFragment());
                }
            });
        }
        priceTv.setText(totalPrice+"");
    }

    private void setDeleteButtonListener(final View cartItem, final int i) {
        RelativeLayout deleteBtn = cartItem.findViewById(R.id.delete_cart_item);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(cartItem, i);
            }
        });
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.page_content, fragment);
        fragmentTransaction.commit();
    }
}
