package com.flickrproject.flickrphotos.Controller;

import com.flickrproject.flickrphotos.Model.ApiResponse;
import com.flickrproject.flickrphotos.Model.DataListener;
import com.flickrproject.flickrphotos.Model.DataModel;
import com.flickrproject.flickrphotos.View.DataView;

public class DataController {
    private DataModel dataModel;
    private DataView dataView;

    public DataController(DataView dataView) {
        this.dataView = dataView;
        dataModel = new DataModel();
    }

    public void fetchData(String textToSearch) {
        dataModel.fetchData(new DataListener() {
            @Override
            public void onDataFetched(ApiResponse response) {
                dataView.displayData(response.getPhotos());
            }

            @Override
            public void onError(String errorMessage) {
                dataView.displayError(errorMessage);
            }
        },
           textToSearch
        );
    }
}
