package com.example.hoodstudio_carpentryapp.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class SelectItem extends AppCompatActivity implements ImageAdapterSelectItem.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapterSelectItem mAdapter;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStrorage;
    private FirebaseAuth firebaseAuth;
    private ValueEventListener mDBListener;
    private FirebaseUser user;
    private List<UserItem> mUploads;
    private List<UserItem> mUploads1;
    private ProgressBar mProgressCircle;
    private FirebaseDatabase firebaseDatabase;

    String getCat;
    String im_name, im_price, im_size, im_detail, im_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        mStrorage = FirebaseStorage.getInstance();

        if (getIntent().hasExtra("cat")) {
            getCat = getIntent().getStringExtra("cat");
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view5);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = (ProgressBar) findViewById(R.id.progress_circle5);

        mUploads = new ArrayList<>();
        mUploads1 = new ArrayList<>();
        mAdapter = new ImageAdapterSelectItem(SelectItem.this, mUploads);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(SelectItem.this);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Items");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot ds : postSnapshot.child(getCat).getChildren()) {
                        UserItem userItem = ds.getValue(UserItem.class);
                        userItem.setKey(ds.getKey());
                        mUploads.add(userItem);
                        UserItem userItem1= postSnapshot.getValue(UserItem.class);
                        userItem1.setKey(postSnapshot.getKey());
                        mUploads1.add(userItem1);
                    }

                }
                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SelectItem.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        UserItem selectUId= mUploads1.get(position);
        final String selectedUID= selectUId.getKey();

        UserItem selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();

        final DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Items").child(selectedUID).child(getCat).child(selectedKey);
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserItem userUpload= dataSnapshot.getValue(UserItem.class);
                im_name = userUpload.getItemName();
                im_price = userUpload.getItemPrice();
                im_size= userUpload.getItemSize();
                im_detail= userUpload.getItemDetail();
                im_pic = userUpload.getItemUrl();

                DatabaseReference ref3= FirebaseDatabase.getInstance().getReference("Basket").child(user.getUid()).child("my");
                UserBasket userBasket = new UserBasket(im_name, im_price, im_detail, im_size, im_pic);
                ref3.child(ref3.push().getKey()).setValue(userBasket);
                startActivity(new Intent(SelectItem.this, CustomerHomePage.class));
                Toast.makeText(SelectItem.this, "Item added to Basket Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SelectItem.this, CustomerHomePage.class));
        finish();
    }
}