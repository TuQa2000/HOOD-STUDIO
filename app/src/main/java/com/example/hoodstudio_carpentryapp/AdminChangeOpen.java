package com.example.hoodstudio_carpentryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.Admin.AdminHome;
import com.example.hoodstudio_carpentryapp.Employee.EmployeeHomePage;
import com.example.hoodstudio_carpentryapp.Employee.UpdateOpen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdminChangeOpen extends AppCompatActivity {
    TextView p_name, p_price, p_size, p_detail, p_cusName, p_cusPhone, p_address;
    String up_name, up_price, up_size, up_detail, up_cusName, up_cusPhone, up_address;
    ImageView image;
    Spinner spinner;
    Button btn_save;
    String p_status;
    String s, value;
    String UID, key;
    private FirebaseAuth firebaseAuth;
    DatabaseReference ref;
    private FirebaseUser user;
    String imageUrl;
    String[] status= {"Processing","Completed"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_open);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("UID") && getIntent().hasExtra("key")){
            UID = getIntent().getStringExtra("UID");
            key= getIntent().getStringExtra("key");
        }

        p_name = findViewById(R.id.t_name9);
        p_price = findViewById(R.id.t_Price9);
        p_size = findViewById(R.id.t_size9);
        p_detail = findViewById(R.id.t_detail9);
        image = findViewById(R.id.pic9);
        btn_save= findViewById(R.id.btn_save9);
        p_cusName= findViewById(R.id.cus_name9);
        p_address= findViewById(R.id.t_address9);
        p_cusPhone= findViewById(R.id.cus_phone9);
        spinner= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(AdminChangeOpen.this, android.R.layout.simple_spinner_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                value= adapterView.getItemAtPosition(i).toString();
                s= value;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ref= FirebaseDatabase.getInstance().getReference("Orders").child(UID).child("own").child(key);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserOrder userOrder= snapshot.getValue(UserOrder.class);
                p_cusName.setText("Customer Name: "+userOrder.getOrderCustomerName());
                up_cusName= userOrder.getOrderCustomerName();
                p_cusPhone.setText("Customer Phone: "+userOrder.getOrderCustomerPhone());
                up_cusPhone= userOrder.getOrderCustomerPhone();
                p_name.setText("Item Name: "+userOrder.getOrderName());
                up_name= userOrder.getOrderName();
                p_price.setText("Price: "+userOrder.getOrderPrice());
                up_price= userOrder.getOrderPrice();
                p_size.setText("Item Size: "+userOrder.getOrderSize());
                up_size=userOrder.getOrderSize();
                p_detail.setText("Item Detail: "+userOrder.getOrderDetail());
                up_detail=userOrder.getOrderDetail();
                p_address.setText("Delivery Address: "+userOrder.getOrderAddress());
                up_address= userOrder.getOrderAddress();
                imageUrl= userOrder.getOrderUrl();
                Picasso.get()
                        .load(userOrder.getOrderUrl())
                        .into(image);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminChangeOpen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    UserOrder userOrder= new UserOrder(up_cusName, up_cusPhone, up_name, up_price, up_detail, up_size, imageUrl, s, up_address);
                    ref.setValue(userOrder);
                    startActivity(new Intent(AdminChangeOpen.this, AdminHome.class));
                    Toast.makeText(AdminChangeOpen.this, "Status changes Updated", Toast.LENGTH_SHORT).show();
                    finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminChangeOpen.this, AdminHome.class));
        finish();
    }
}