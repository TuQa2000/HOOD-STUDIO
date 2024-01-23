package com.example.hoodstudio_carpentryapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hoodstudio_carpentryapp.R;
import com.google.android.material.badge.BadgeUtils;

public class AdminSelectCategory extends AppCompatActivity {
    ImageButton office, bedroom, door;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_category);

        office= findViewById(R.id.office);
        bedroom= findViewById(R.id.bedroom);
        door= findViewById(R.id.door);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "office";
                Intent intent = new Intent(AdminSelectCategory.this, AddItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "bedroom";
                Intent intent = new Intent(AdminSelectCategory.this, AddItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "door";
                Intent intent = new Intent(AdminSelectCategory.this, AddItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminSelectCategory.this, AdminHome.class));
        finish();}
}