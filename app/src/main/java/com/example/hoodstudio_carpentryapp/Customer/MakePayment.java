package com.example.hoodstudio_carpentryapp.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserBasket;
import com.example.hoodstudio_carpentryapp.UserInformation;
import com.example.hoodstudio_carpentryapp.UserOrder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class MakePayment extends AppCompatActivity {
    TextView bo_name, bo_price, bo_detail, bo_size;
    EditText credit, cus_address;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    Button confirm;
    String user_credit, text, user_address;
    String getKey;
    String img_price, img_name, img_size, img_detail, img_url;
    ImageView imgGet;

    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    DatabaseReference ref;
    String user_name, user_phone;
    String status= "Processing";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);

        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        confirm= (Button) findViewById(R.id.confirm);
        bo_name= (TextView) findViewById(R.id.bo_name);
        bo_price= (TextView) findViewById(R.id.bo_price);
        bo_size= (TextView) findViewById(R.id.bo_size);
        bo_detail= (TextView) findViewById(R.id.bo_detail);
        credit= (EditText) findViewById(R.id.credit);
        cus_address= (EditText) findViewById(R.id.cus_Address);
        imgGet= (ImageView) findViewById(R.id.imgGet);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (getIntent().hasExtra("key")) {
            getKey = getIntent().getStringExtra("key");
        }

        credit.setVisibility(View.INVISIBLE);

        mStrorage = FirebaseStorage.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Basket").child(user.getUid()).child("my").child(getKey);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                        UserBasket userBasket = snapshot.getValue(UserBasket.class);
                        bo_name.setText(userBasket.getBasName());
                        bo_price.setText(userBasket.getBasPrice());
                        bo_detail.setText(userBasket.getBasDetail());
                        bo_size.setText(userBasket.getBasSize());
                    Picasso.get()
                            .load(userBasket.getBasUrl())
                            .placeholder(R.mipmap.ic_launcher)
                            .fit()
                            .centerCrop()
                            .into(imgGet);

                        img_name= userBasket.getBasName();
                        img_price= userBasket.getBasPrice();
                        img_size= userBasket.getBasSize();
                        img_detail= userBasket.getBasDetail();
                        img_url= userBasket.getBasUrl();

                }
                if (!snapshot.exists()){
                    Toast.makeText(MakePayment.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MakePayment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
                user_name = userInformation.getUserName();
                user_phone= userInformation.getUserPhone();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MakePayment.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                text= radioButton.getText().toString();
                if (text.equals("Cash on delivery")) {
                    if (validate1()){
                    startActivity(new Intent(MakePayment.this, CustomerHomePage.class));
                    orderSave();
                    Toast.makeText(MakePayment.this, "Payment will be done as cash on delivery", Toast.LENGTH_SHORT).show();
                    finish();
                }
                } else {
                    credit.setVisibility(View.VISIBLE);
                    if (validate1()){
                    if (validate()) {
                        startActivity(new Intent(MakePayment.this, CustomerHomePage.class));
                        orderSave();
                        Toast.makeText(MakePayment.this, "Payment done successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }//validate end
                }
                }
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_credit = credit.getText().toString();

        if(user_credit.isEmpty()){
            Toast.makeText(this, "Enter credit card number", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    private Boolean validate1(){
        boolean result= false;

        user_address= cus_address.getText().toString();

        if(user_address.isEmpty()){
            Toast.makeText(this, "Enter address for delivery", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    public void orderSave(){
        DatabaseReference ref2= FirebaseDatabase.getInstance().getReference("Orders").child(user.getUid()).child("own");
        UserOrder userOrder = new UserOrder(user_name, user_phone, img_name, img_price, img_detail, img_size,img_url, status, user_address);
        ref2.child(ref2.push().getKey()).setValue(userOrder);
        ref.getRef().removeValue();
        Toast.makeText(MakePayment.this, "Order Confirmed Successfully", Toast.LENGTH_SHORT).show();
        Toast.makeText(MakePayment.this, "Thank you for choosing us", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MakePayment.this, CustomerHomePage.class));
        finish();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MakePayment.this, CustomerBasket.class));
        finish();
    }
}