package com.example.hoodstudio_carpentryapp.Employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoodstudio_carpentryapp.Admin.ImageAdapterChangeOrderStatus;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserOrder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterEmpChangeStatus extends RecyclerView.Adapter <ImageAdapterEmpChangeStatus.ImageViewHolder>{

    private Context mContext;
    private List<UserOrder> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterEmpChangeStatus(Context context, List<UserOrder> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_emp_change_order_status, parent, false);
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

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textViewName;
        public TextView textViewCusName;
        public TextView textViewCusPhone;
        public TextView textViewStatus;
        public TextView textViewPrice;
        public TextView textViewDetail;
        public TextView textViewSize;
        public ImageView imageView;
        public TextView textViewAddress;
        public Button btn_orderStatus;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCusName= itemView.findViewById(R.id.image_view_cus_name10);
            textViewCusPhone= itemView.findViewById(R.id.image_view_cus_phone10);
            textViewStatus= itemView.findViewById(R.id.image_view_status10);
            textViewName= itemView.findViewById(R.id.image_view_name10);
            textViewPrice= itemView.findViewById(R.id.image_view_price10);
            textViewSize= itemView.findViewById(R.id.image_view_size10);
            textViewDetail= itemView.findViewById(R.id.image_view_detail10);
            imageView= itemView.findViewById(R.id.image_view_uploaded10);
            textViewAddress= itemView.findViewById(R.id.image_view_address10);
            btn_orderStatus= itemView.findViewById(R.id.btn_orderStatus10);

            btn_orderStatus.setOnClickListener(this);

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
