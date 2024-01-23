package com.example.hoodstudio_carpentryapp.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.Admin.AdminHome;
import com.example.hoodstudio_carpentryapp.AdminChangeOpen;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerSettings extends AppCompatActivity {
    TextView s_about, s_logout, s_profile, s_order;

    public static final String SHARED_PREFS= "sharedPrefs";

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        s_about= findViewById(R.id.s_about);
        s_logout= findViewById(R.id.s_logout);
        s_profile= findViewById(R.id.s_profile);
        s_order= findViewById(R.id.s_order);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        s_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerSettings.this, About.class));
                finish();
            }
        });

        s_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.commit();
                firebaseAuth.signOut();
                finish();
                Toast.makeText(CustomerSettings.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerSettings.this, Users.class));
            }
        });

        s_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerSettings.this, CustomerProfile.class));
                finish();
            }
        });

        s_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerSettings.this, CusViewOrder.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CustomerSettings.this, CustomerHomePage.class));
        finish();
    }
}