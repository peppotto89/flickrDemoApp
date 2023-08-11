package com.flickrproject.flickrphotos.Api;

import com.flickrproject.flickrphotos.Model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/services/rest/?method=flickr.photos.search&api_key=123b3d85adcf545417f2571d1470c916&format=json&nojsoncallback=1")
    Call<ApiResponse> searchPhotos(@Query("text") String searchTerm);
}
