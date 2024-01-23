package com.example.hoodstudio_carpentryapp.Customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoodstudio_carpentryapp.Employee.ImageAdapterUpdateItem;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserBasket;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterCustomerBasket extends RecyclerView.Adapter <ImageAdapterCustomerBasket.ImageViewHolder>{

    private Context mContext;
    private List<UserBasket> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterCustomerBasket(Context context, List<UserBasket> userUploads){
        mContext= context;
        mUploads= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_customer_basket, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserBasket uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Item Name: "+uploadCurrent.getBasName());
        holder.textViewPrice.setText("Item Price: "+uploadCurrent.getBasPrice()+" OMR");
        holder.textViewSize.setText("Item Size:  "+uploadCurrent.getBasSize());
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getBasDetail());
        Picasso.get()
                .load(uploadCurrent.getBasUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewSize;
        public ImageView imageView;
        public Button btn_basketPay;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name6);
            textViewPrice= itemView.findViewById(R.id.image_view_price6);
            textViewSize= itemView.findViewById(R.id.image_view_size6);
            textViewDetail= itemView.findViewById(R.id.image_view_detail6);
            imageView= itemView.findViewById(R.id.image_view_uploaded6);
            btn_basketPay= itemView.findViewById(R.id.basket_pay6);

            btn_basketPay.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }


    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener= listener;
}
}
