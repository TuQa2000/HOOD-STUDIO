package com.example.hoodstudio_carpentryapp.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hoodstudio_carpentryapp.Employee.EmployeeHomePage;
import com.example.hoodstudio_carpentryapp.Employee.EmployeeSelectCategory;
import com.example.hoodstudio_carpentryapp.Employee.UpdateItem;
import com.example.hoodstudio_carpentryapp.R;

public class CustomerSelectcategory extends AppCompatActivity {
    ImageButton office, bedroom, door;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_selectcategory);

        office= findViewById(R.id.office3);
        bedroom= findViewById(R.id.bedroom3);
        door= findViewById(R.id.door3);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "office";
                Intent intent = new Intent(CustomerSelectcategory.this, SelectItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "bedroom";
                Intent intent = new Intent(CustomerSelectcategory.this, SelectItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "door";
                Intent intent = new Intent(CustomerSelectcategory.this, SelectItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CustomerSelectcategory.this, CustomerHomePage.class));
        finish();
    }
    }
