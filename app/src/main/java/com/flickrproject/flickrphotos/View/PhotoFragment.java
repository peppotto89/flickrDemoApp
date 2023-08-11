package com.flickrproject.flickrphotos.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flickrproject.flickrphotos.Model.ApiResponse;
import com.flickrproject.flickrphotos.R;

public class PhotoFragment extends Fragment {

    private ImageView imageView;
    private TextView idText;
    private TextView titleText;
    private ApiResponse.Photo photo;

    public static PhotoFragment newInstance(ApiResponse.Photo photo) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putParcelable("photo", photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo, container, false);

        idText = rootView.findViewById(R.id.id);
        titleText = rootView.findViewById(R.id.title);
        imageView = rootView.findViewById(R.id.imageView);

        if (getArguments() != null) {
            photo = getArguments().getParcelable("photo");
        }
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder);
        Glide.with(requireContext())
                .setDefaultRequestOptions(requestOptions)
                .load(getPhotoUrl(photo))
                .fitCenter()
                .into(imageView);
        idText.setText(String.format("Photo id: %s", photo.getId()));
        titleText.setText(String.format("Title: %s", photo.getTitle()));

        return rootView;
    }

    private String getPhotoUrl(ApiResponse.Photo photo) {
        return "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
    }
}
