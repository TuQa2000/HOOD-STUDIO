package com.example.hoodstudio_carpentryapp.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.Employee.EmployeeHomePage;
import com.example.hoodstudio_carpentryapp.Employee.ImageAdapterUpdateItem;
import com.example.hoodstudio_carpentryapp.Employee.UpdateItem;
import com.example.hoodstudio_carpentryapp.Employee.UpdateOpen;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserBasket;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class CustomerBasket extends AppCompatActivity implements ImageAdapterCustomerBasket.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterCustomerBasket mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserBasket> mUploads;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;
    TextView b_home, b_basket, b_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_basket);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        b_basket= findViewById(R.id.b_basket1);
        b_home= findViewById(R.id.b_home1);
        b_setting= findViewById(R.id.b_setting1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view6);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle6);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapterCustomerBasket(CustomerBasket.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(CustomerBasket.this);

        mStrorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Basket").child(user.getUid()).child("my");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        if (postSnapshot.exists()) {
                            UserBasket userBasket = postSnapshot.getValue(UserBasket.class);
                            userBasket.setKey(postSnapshot.getKey());
                            mUploads.add(userBasket);
                        }
                    if (!postSnapshot.exists()) {
                        Toast.makeText(CustomerBasket.this, "", Toast.LENGTH_SHORT).show();
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
            }
                    mAdapter.notifyDataSetChanged();
                    mProgressCircle.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerBasket.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


        b_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomerBasket.this, "You are already at Basket", Toast.LENGTH_SHORT).show();
            }
        });
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerBasket.this, CustomerHomePage.class));
                finish();
            }
        });

        b_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerBasket.this, CustomerSettings.class));
                finish();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        UserBasket selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        Intent intent = new Intent(CustomerBasket.this, MakePayment.class);
        intent.putExtra("key", selectedKey);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CustomerBasket.this, CustomerHomePage.class));
        finish();
    }
}