package com.example.yomna.dawaiacom.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.yomna.dawaiacom.HomePageActivity;
import com.example.yomna.dawaiacom.R;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.username_et);
        etPassword = findViewById(R.id.password_et);
    }
    public void login(View view){
        //TODO: check if username and password exist and match
        openHomePage();
    }
    public void openHomePage(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ) {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }
}
