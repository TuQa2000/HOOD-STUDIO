package com.example.hoodstudio_carpentryapp.Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmployeeHomePage extends AppCompatActivity {

    Button view, change, update, logout;

    public static final String SHARED_PREFS= "sharedPrefs";

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home_page);

        view= findViewById(R.id.view1);
        change= findViewById(R.id.change1);
        update= findViewById(R.id.update);
        logout= findViewById(R.id.logout1);

        firebaseAuth= FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeHomePage.this, EmpViewOrders.class));
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeHomePage.this, EmployeeSelectCategory.class));
                finish();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeHomePage.this, EmpChangeStatusOrder.class));
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
                Toast.makeText(EmployeeHomePage.this, "Account Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EmployeeHomePage.this, Users.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}