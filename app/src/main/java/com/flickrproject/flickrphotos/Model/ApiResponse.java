package com.flickrproject.flickrphotos.Model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ApiResponse {
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public class Photos {
        private int page;
        private int pages;
        private int perpage;
        private int total;
        private List<Photo> photo;

        public int getPage() {
            return page;
        }

        public int getPages() {
            return pages;
        }

        public int getPerpage() {
            return perpage;
        }

        public int getTotal() {
            return total;
        }

        public List<Photo> getPhoto() {
            return photo;
        }
    }

    @SuppressLint("ParcelCreator")
    public class Photo implements Parcelable {
        private String id;
        private String owner;
        private String secret;
        private String server;
        private int farm;
        private String title;
        private int ispublic;
        private int isfriend;
        private int isfamily;

        public String getId() {
            return id;
        }

        public String getOwner() {
            return owner;
        }

        public String getSecret() {
            return secret;
        }

        public String getServer() {
            return server;
        }

        public int getFarm() {
            return farm;
        }

        public String getTitle() {
            return title;
        }

        public int getIspublic() {
            return ispublic;
        }

        public int getIsfriend() {
            return isfriend;
        }

        public int getIsfamily() {
            return isfamily;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

        }
    }
}