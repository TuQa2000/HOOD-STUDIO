package com.example.hoodstudio_carpentryapp.Employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.Admin.DeleteItem;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UpdateOpen extends AppCompatActivity {
    private static final String TAG = "ImageOpen";
    EditText p_name, p_price, p_size, p_detail;
    ImageView image;
    Button update_service;
    String u_s_name, u_s_price, u_s_size, u_s_detail;
    String UID, key, getCat;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    private FirebaseUser user;
    String imageUrl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_open);


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("UID") && getIntent().hasExtra("key")
         && getIntent().hasExtra("cat")) {
            UID = getIntent().getStringExtra("UID");
            key= getIntent().getStringExtra("key");
            getCat= getIntent().getStringExtra("cat");
        }

        p_name = findViewById(R.id.pic_name);
        p_price = findViewById(R.id.pic_Price);
        p_size = findViewById(R.id.pic_size);
        p_detail = findViewById(R.id.pic_detail);
        image = findViewById(R.id.pic);
        update_service= findViewById(R.id.update_item);

        ref= FirebaseDatabase.getInstance().getReference("Items").child(UID).child(getCat).child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserItem userItem= snapshot.getValue(UserItem.class);
                    p_name.setText(userItem.getItemName());
                    p_price.setText(userItem.getItemPrice());
                    p_size.setText(userItem.getItemSize());
                    p_detail.setText(userItem.getItemDetail());
                    imageUrl= userItem.getItemUrl();
                    Picasso.get()
                        .load(userItem.getItemUrl())
                        .into(image);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateOpen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        update_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    UserItem userItem = new UserItem(u_s_name, u_s_price, u_s_detail, u_s_size, imageUrl);
                    ref.setValue(userItem);
                    startActivity(new Intent(UpdateOpen.this, EmployeeHomePage.class));
                    Toast.makeText(UpdateOpen.this, "Item Details are Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    private Boolean validate(){
        boolean result= false;

        u_s_name = p_name.getText().toString();
        u_s_price = p_price.getText().toString();
        u_s_size= p_size.getText().toString();
        u_s_detail = p_detail.getText().toString();
        if(u_s_name.isEmpty() || u_s_price.isEmpty() || u_s_size.isEmpty() || u_s_detail.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateOpen.this, EmployeeHomePage.class));
        finish();
    }
}