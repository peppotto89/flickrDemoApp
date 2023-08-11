package com.flickrproject.flickrphotos.Model;

public interface DataListener {
    void onDataFetched(ApiResponse response);
    void onError(String errorMessage);
}
