package com.example.hoodstudio_carpentryapp.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.Admin.AdminHome;
import com.example.hoodstudio_carpentryapp.AdminChangeOpen;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerHomePage extends AppCompatActivity {
    Button viewOrder, addNewOrder, customOrder, logout, basket;
    TextView b_home, b_basket, b_setting;

    public static final String SHARED_PREFS= "sharedPrefs";

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        viewOrder= findViewById(R.id.viewOrder);
        addNewOrder= findViewById(R.id.addNewOrder);
        customOrder= findViewById(R.id.customOrder);
        basket= findViewById(R.id.basket);
        logout= findViewById(R.id.logout3);
        b_basket= findViewById(R.id.b_basket);
        b_home= findViewById(R.id.b_home);
        b_setting= findViewById(R.id.b_setting);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        b_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CustomerBasket.class));
                finish();
            }
        });
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerHomePage.this, "You are already at home Screen", Toast.LENGTH_SHORT).show();
            }
        });

        b_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CustomerSettings.class));
                finish();
            }
        });

        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CusViewOrder.class));
                finish();
            }
        });

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CustomerBasket.class));
                finish();
            }
        });

        customOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CustomizedOrder.class));
                finish();
            }
        });

        addNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerHomePage.this, CustomerSelectcategory.class));
                finish();
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.commit();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(CustomerHomePage.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerHomePage.this, Users.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}