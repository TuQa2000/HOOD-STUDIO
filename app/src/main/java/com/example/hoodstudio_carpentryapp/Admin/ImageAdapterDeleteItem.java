package com.example.hoodstudio_carpentryapp.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterDeleteItem extends RecyclerView.Adapter <ImageAdapterDeleteItem.ImageViewHolder>{

    private Context mContext;
    private List<UserItem> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterDeleteItem(Context context, List<UserItem> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_delete_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        UserItem uploadCurrent= mUploads.get(position);
        holder.textViewName.setText("Item Name: "+uploadCurrent.getItemName());
        holder.textViewPrice.setText("Item Price: "+uploadCurrent.getItemPrice()+" OMR");
        holder.textViewSize.setText("Item Size:  "+uploadCurrent.getItemSize());
        holder.textViewDetail.setText("Item Details: "+uploadCurrent.getItemDetail());
        Picasso.get()
                .load(uploadCurrent.getItemUrl())
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
        public Button btn_delete;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name);
            textViewPrice= itemView.findViewById(R.id.image_view_price);
            textViewSize= itemView.findViewById(R.id.image_view_size);
            textViewDetail= itemView.findViewById(R.id.image_view_detail);
            imageView= itemView.findViewById(R.id.image_view_uploaded);
            btn_delete= itemView.findViewById(R.id.delete_item);

            btn_delete.setOnClickListener(this);

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
