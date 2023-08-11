package com.flickrproject.flickrphotos.View;

import com.flickrproject.flickrphotos.Model.ApiResponse;

public interface DataView {
    void displayData(ApiResponse.Photos data);
    void displayError(String error);
}
