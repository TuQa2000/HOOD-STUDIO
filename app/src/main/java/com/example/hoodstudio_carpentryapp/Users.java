package com.example.hoodstudio_carpentryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hoodstudio_carpentryapp.Admin.AdminHome;
import com.example.hoodstudio_carpentryapp.Admin.AdminLogin;
import com.example.hoodstudio_carpentryapp.Customer.CustomerHomePage;
import com.example.hoodstudio_carpentryapp.Customer.CustomerLogin;
import com.example.hoodstudio_carpentryapp.Employee.EmployeeHomePage;
import com.example.hoodstudio_carpentryapp.Employee.EmployeeLogin;

public class Users extends AppCompatActivity {
    Button customer, employee, admin;
    public static final String SHARED_PREFS= "sharedPrefs";
    String ff1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        customer= (Button) findViewById(R.id.customer);
        employee= (Button) findViewById(R.id.employee);
        admin= (Button) findViewById(R.id.admin);

        checkBox();

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Users.this, CustomerLogin.class));
                finish();
            }
        });

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Users.this, EmployeeLogin.class));
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Users.this, AdminLogin.class));
                finish();
            }
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check= sharedPreferences.getString("name", "");

            if (check.equals("customer")){
                startActivity(new Intent(Users.this, CustomerHomePage.class));
                finish();
            }
            if (check.equals("employee")){
                startActivity(new Intent(Users.this, EmployeeHomePage.class));
                finish();
            }
            if (check.equals("admin")){
                startActivity(new Intent(Users.this, AdminHome.class));
                finish();
            }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}