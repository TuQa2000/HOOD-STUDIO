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

import com.example.hoodstudio_carpentryapp.Admin.ImageAdapterDeleteItem;
import com.example.hoodstudio_carpentryapp.R;
import com.example.hoodstudio_carpentryapp.UserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapterUpdateItem extends RecyclerView.Adapter <ImageAdapterUpdateItem.ImageViewHolder>{

    private Context mContext;
    private List<UserItem> mUploads, mUploads1;
    private OnItemClickListener mListener;

    public ImageAdapterUpdateItem(Context context, List<UserItem> userUploads){
        mContext= context;
        mUploads= userUploads;
        mUploads1= userUploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_view_update_item, parent, false);
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
        public Button btn_update;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName= itemView.findViewById(R.id.image_view_name2);
            textViewPrice= itemView.findViewById(R.id.image_view_price2);
            textViewSize= itemView.findViewById(R.id.image_view_size2);
            textViewDetail= itemView.findViewById(R.id.image_view_detail2);
            imageView= itemView.findViewById(R.id.image_view_uploaded2);
            btn_update= itemView.findViewById(R.id.update_item);

            btn_update.setOnClickListener(this);

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
