package com.example.hoodstudio_carpentryapp.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerProfile extends AppCompatActivity {
    private EditText u_name, u_phone;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Button update;
    String user_fname, user_uphone;
    NetworkInfo nInfo;
    private FirebaseDatabase firebaseDatabase;
    String ff1;

    TextView b_home, b_basket, b_setting;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        if (getIntent().hasExtra("type")) {
            ff1 = getIntent().getStringExtra("type");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        update=(Button) findViewById(R.id.update);
        u_name= (EditText)findViewById(R.id.u_name);
        u_phone= (EditText)findViewById(R.id.u_phone);
        b_basket= findViewById(R.id.b_basket2);
        b_home= findViewById(R.id.b_home2);
        b_setting= findViewById(R.id.b_setting2);

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        //get firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Profile");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInformation userInformation= dataSnapshot.getValue(UserInformation.class);
                u_name.setText(userInformation.getUserName());
                u_phone.setText(userInformation.getUserPhone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(CustomerProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        b_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerProfile.this, CustomerBasket.class));
                finish();
            }
        });
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerProfile.this, CustomerHomePage.class));
                finish();
            }
        });

        b_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerProfile.this, CustomerSettings.class));
                finish();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    if (validate()) {
                        if (nInfo != null && nInfo.isConnected()) {
                            UserInformation userInformation = new UserInformation(user_fname, user_uphone);
                            ref.setValue(userInformation);
                            Toast.makeText(CustomerProfile.this, "Profile is Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CustomerProfile.this, CustomerProfile.class));
                                finish();
                        }
                    } else {
                        Toast.makeText(CustomerProfile.this, "Network is not available", Toast.LENGTH_LONG).show();
                    }
                }//user null
            }
        });
    }

    private Boolean validate(){
        boolean result= false;

        user_fname = u_name.getText().toString();
        user_uphone=u_phone.getText().toString();
        if( user_fname.isEmpty() || user_uphone.isEmpty()){
            Toast.makeText(this, "Fill every required information", Toast.LENGTH_SHORT).show();
        }else {
            result= true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
            startActivity(new Intent(CustomerProfile.this, CustomerHomePage.class));
            finish();
    }
}