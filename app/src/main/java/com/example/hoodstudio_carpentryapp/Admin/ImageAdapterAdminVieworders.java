package com.example.hoodstudio_carpentryapp.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoodstudio_carpentryapp.Employee.ImageAdapterEmpViewOrder;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserOrder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterAdminVieworders extends RecyclerView.Adapter <ImageAdapterAdminVieworders.ImageViewHolder>{

    private Context mContext;
    private List<UserOrder> mUploads, mUploads1;

    public ImageAdapterAdminVieworders(Context context, List<UserOrder> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_admin_view_orders, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserOrder uploadCurrent= mUploads.get(position);
        holder.textViewCusName.setText("Customer Name: "+uploadCurrent.getOrderCustomerName());
        holder.textViewCusPhone.setText("Customer Phone: "+uploadCurrent.getOrderCustomerPhone());
        holder.textViewStatus.setText("Order Status: "+uploadCurrent.getOrderStatus());
        holder.textViewName.setText("Item Name: "+uploadCurrent.getOrderName());
        holder.textViewPrice.setText("Item Price: "+uploadCurrent.getOrderPrice()+" OMR");
        holder.textViewSize.setText("Item Size:  "+uploadCurrent.getOrderSize());
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getOrderDetail());
        holder.textViewAddress.setText("Delivery Address: "+uploadCurrent.getOrderAddress());
        Picasso.get()
                .load(uploadCurrent.getOrderUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewCusName;
        public TextView textViewCusPhone;
        public TextView textViewStatus;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewSize;
        public ImageView imageView;
        public TextView textViewAddress;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCusName= itemView.findViewById(R.id.image_view_cus_name1);
            textViewCusPhone= itemView.findViewById(R.id.image_view_cus_phone1);
            textViewStatus= itemView.findViewById(R.id.image_view_status1);
            textViewName= itemView.findViewById(R.id.image_view_name1);
            textViewPrice= itemView.findViewById(R.id.image_view_price1);
            textViewSize= itemView.findViewById(R.id.image_view_size1);
            textViewDetail= itemView.findViewById(R.id.image_view_detail1);
            imageView= itemView.findViewById(R.id.image_view_uploaded1);
            textViewAddress= itemView.findViewById(R.id.image_view_address1);

        }
    }
}
