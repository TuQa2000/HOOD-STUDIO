package com.example.hoodstudio_carpentryapp.Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hoodstudio_carpentryapp.Admin.AddItem;
import com.example.hoodstudio_carpentryapp.Admin.AdminHome;
import com.example.hoodstudio_carpentryapp.Admin.AdminSelectCategory;
import com.example.hoodstudio_carpentryapp.R;

public class EmployeeSelectCategory extends AppCompatActivity {

    ImageButton office, bedroom, door;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_select_category);

        office= findViewById(R.id.office2);
        bedroom= findViewById(R.id.bedroom2);
        door= findViewById(R.id.door2);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "office";
                Intent intent = new Intent(EmployeeSelectCategory.this, UpdateItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "bedroom";
                Intent intent = new Intent(EmployeeSelectCategory.this, UpdateItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "door";
                Intent intent = new Intent(EmployeeSelectCategory.this, UpdateItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EmployeeSelectCategory.this, EmployeeHomePage.class));
        finish();
    }
}