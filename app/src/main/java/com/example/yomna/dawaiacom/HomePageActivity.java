package com.example.yomna.dawaiacom;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setFragment(new OffersFragment());
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.page_content, fragment);
        fragmentTransaction.commit();
    }

    public void openCart(View view) {
        setFragment(new CartFragment());
    }

    public void openHome(View view) {
        setFragment(new OffersFragment());
    }
}
