package com.example.hoodstudio_carpentryapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hoodstudio_carpentryapp.R;

public class AdminSelectCategory1 extends AppCompatActivity {

    ImageButton office, bedroom, door;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_category1);

        office= findViewById(R.id.office1);
        bedroom= findViewById(R.id.bedroom1);
        door= findViewById(R.id.door1);

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "office";
                Intent intent = new Intent(AdminSelectCategory1.this, DeleteItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "bedroom";
                Intent intent = new Intent(AdminSelectCategory1.this, DeleteItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });

        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= "door";
                Intent intent = new Intent(AdminSelectCategory1.this, DeleteItem.class);
                intent.putExtra("cat", category);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminSelectCategory1.this, AdminHome.class));
        finish();}
}