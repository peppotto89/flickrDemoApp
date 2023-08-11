package com.flickrproject.flickrphotos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flickrproject.flickrphotos.Model.ApiResponse;
import com.flickrproject.flickrphotos.R;

import java.util.List;

public class PhotoDataAdapter extends RecyclerView.Adapter<PhotoDataAdapter.ViewHolder> {
    private List<ApiResponse.Photo> photoList;
    private Context context;
    private OnPhotoClickListener photoClickListener;

    public interface OnPhotoClickListener {
        void onPhotoClick(ApiResponse.Photo photo);
    }

    public PhotoDataAdapter(Context context, List<ApiResponse.Photo> photoList, OnPhotoClickListener listener) {
        this.context = context;
        this.photoList = photoList;
        this.photoClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiResponse.Photo photo = photoList.get(position);

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(getPhotoUrl(photo))
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    private String getPhotoUrl(ApiResponse.Photo photo) {
        return "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            itemView.setOnClickListener(v -> {
                if (photoClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ApiResponse.Photo clickedPhoto = photoList.get(position);
                        photoClickListener.onPhotoClick(clickedPhoto);
                    }
                }
            });
        }
    }
}
