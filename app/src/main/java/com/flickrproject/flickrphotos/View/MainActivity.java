package com.flickrproject.flickrphotos.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.flickrproject.flickrphotos.Model.ApiResponse;
import com.flickrproject.flickrphotos.R;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(MainFragment.newInstance(), false);

    }

    private void loadFragment(Fragment fragment, Boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        if(addToBackStack) fragmentTransaction.addToBackStack("photoDetail");
        fragmentTransaction.commit();
    }

    @Override
    public void openPhoto(ApiResponse.Photo photo) {
        loadFragment(PhotoFragment.newInstance(photo), true);
    }
}
