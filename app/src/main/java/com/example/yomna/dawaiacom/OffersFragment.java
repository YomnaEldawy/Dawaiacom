package com.example.yomna.dawaiacom;


import android.graphics.Color;
import android.graphics.Paint;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment {

    View v;
    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_offers, container, false);
        LinearLayout root = v.findViewById(R.id.offers_linear_layout);
        ArrayList<Product> products = new ArrayList<Product>();
        initializeProducts(products);
        int index = 0;
        for (int i = 0; i <= products.size()/2; i++){
            LinearLayout rowLayout = new LinearLayout(getContext());
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 2; j++){
                if (index >= products.size())
                    break;
                View view = getProductView(products.get(index));
                setOnAddToCartListener(view, products.get(index));
                Cart cart = Cart.getInstance();
                RelativeLayout addToCart = view.findViewById(R.id.add_to_cart);
                RelativeLayout addedToCart = view.findViewById(R.id.added_to_cart);
                if(cart.isInCart(products.get(index))){
                    addToCart.setVisibility(View.INVISIBLE);
                    addedToCart.setVisibility(View.VISIBLE);
                }
                else{
                    addToCart.setVisibility(View.VISIBLE);
                    addedToCart.setVisibility(View.INVISIBLE);
                }
                index++;
                rowLayout.addView(view);
            }
            root.addView(rowLayout);
        }

        return v;
    }

    View getProductView(Product p){
        View view = getView().inflate(getContext(), R.layout.product, null);
        TextView productNameTv = view.findViewById(R.id.item_name);
        TextView priceTv = view.findViewById(R.id.price_tv);
        ImageView imageView = view.findViewById(R.id.product_img);
        imageView.setImageResource(p.getImage());
        productNameTv.setText(p.getName());
        priceTv.setText(p.getBasicPrice() + " EGP");
        if (p.getDiscount() > 0){
           priceTv.setPaintFlags(priceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
           RelativeLayout discountBar = view.findViewById(R.id.discount_bar);
           discountBar.setVisibility(View.VISIBLE);
           TextView discountValue = view.findViewById(R.id.discount_value);
           discountValue.setText(p.getDiscount() * 100 + "%");
           TextView newPrice = view.findViewById(R.id.new_price_tv);
           newPrice.setVisibility(View.VISIBLE);
           newPrice.setText(p.getCurrentPrice() + " EGP");
        }
        return view;
    }

    void initializeProducts(ArrayList<Product> products){
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Sudocrem");
        product1.setBasicPrice(30);
        product1.setImage(R.drawable.sudocrem);
        products.add(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Pampers");
        product2.setBasicPrice(50);
        product2.setImage(R.drawable.pampers);
        product2.setDiscount(0.22);
        products.add(product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setName("Garnier Day Cream");
        product3.setBasicPrice(25);
        product3.setImage(R.drawable.garnier);
        products.add(product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setName("Neutrogenea Sunscreen");
        product4.setBasicPrice(289);
        product4.setImage(R.drawable.neutrogeneasunscreen);
        products.add(product4);


        Product product5 = new Product();
        product5.setId(5);
        product5.setName("Himalaya Face wash");
        product5.setBasicPrice(69);
        product5.setDiscount(0.15);
        product5.setImage(R.drawable.himalayafacewash);
        products.add(product5);
    }

    void setOnAddToCartListener(final View view, final Product product){
        RelativeLayout cartIcon = view.findViewById(R.id.cart_status);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = Cart.getInstance();
                RelativeLayout addToCart = view.findViewById(R.id.add_to_cart);
                RelativeLayout addedToCart = view.findViewById(R.id.added_to_cart);
                if (cart.isInCart(product)) {
                    addToCart.setVisibility(View.VISIBLE);
                    addedToCart.setVisibility(View.INVISIBLE);
                    cart.removeItem(product);
                }else{
                    addToCart.setVisibility(View.INVISIBLE);
                    addedToCart.setVisibility(View.VISIBLE);
                    cart.addToCart(product);
                }
                updateBubble();
            }
        });
    }

    void updateBubble(){
        Cart cart = Cart.getInstance();
        RelativeLayout cartItemsContainer = getActivity().findViewById(R.id.items_count_container);
        TextView itemsCount = getActivity().findViewById(R.id.items_count_value);
        if(cart.getItems().size() > 0)
            cartItemsContainer.setVisibility(View.VISIBLE);
        else
            cartItemsContainer.setVisibility(View.INVISIBLE);
        itemsCount.setText(cart.getItems().size() + "");
    }


}
