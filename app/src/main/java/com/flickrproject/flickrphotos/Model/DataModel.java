package com.flickrproject.flickrphotos.Model;

import com.flickrproject.flickrphotos.Api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataModel {
    private String baseUrl = "https://www.flickr.com";
    private Retrofit retrofit;

    public DataModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void fetchData(final DataListener listener, String textToSearch) {
        ApiService apiService = retrofit.create(ApiService.class);

        Call<ApiResponse> call = apiService.searchPhotos(textToSearch);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onDataFetched(response.body());
                } else {
                    listener.onError("API request failed");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onError("API request failed");
            }
        });
    }
}
