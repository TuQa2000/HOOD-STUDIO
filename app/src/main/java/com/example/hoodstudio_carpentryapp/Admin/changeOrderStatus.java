package com.example.hoodstudio_carpentryapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hoodstudio_carpentryapp.AdminChangeOpen;
import com.example.hoodstudio_carpentryapp.Customer.CusViewOrder;
import com.example.hoodstudio_carpentryapp.Customer.CustomerHomePage;
import com.example.hoodstudio_carpentryapp.Customer.ImageAdapterCusViewOrder;
import com.example.hoodstudio_carpentryapp.Customer.ImageAdapterSelectItem;
import com.example.hoodstudio_carpentryapp.Customer.SelectItem;
import com.example.hoodstudio_carpentryapp.Employee.EmpChangeStatusOrder;
import com.example.hoodstudio_carpentryapp.Employee.UpdateItem;
import com.example.hoodstudio_carpentryapp.Employee.UpdateOpen;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserBasket;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.example.hoodstudio_carpentryapp.UserOrder;
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

public class changeOrderStatus extends AppCompatActivity implements ImageAdapterChangeOrderStatus.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterChangeOrderStatus mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;

    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserOrder> mUploads, mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_order_status);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view8);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle8);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterChangeOrderStatus(changeOrderStatus.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(changeOrderStatus.this);

        mStrorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Orders");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds: postSnapshot.child("own").getChildren()){
                        UserOrder userupload= ds.getValue(UserOrder.class);
                        userupload.setKey(ds.getKey());
                        mUploads.add(userupload);
                        UserOrder userOrder1= postSnapshot.getValue(UserOrder.class);
                        userOrder1.setKey(postSnapshot.getKey());
                        mUploads1.add(userOrder1);
                    }
                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(changeOrderStatus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        UserOrder selectUId = mUploads1.get(position);
        final String selectedUID = selectUId.getKey();

        UserOrder selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        Intent intent = new Intent(changeOrderStatus.this, AdminChangeOpen.class);
        intent.putExtra("UID", selectedUID);
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
        startActivity(new Intent(changeOrderStatus.this, AdminHome.class));
        finish();
    }
}