package com.flickrproject.flickrphotos.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flickrproject.flickrphotos.Adapter.PhotoDataAdapter;
import com.flickrproject.flickrphotos.Controller.DataController;
import com.flickrproject.flickrphotos.Model.ApiResponse;
import com.flickrproject.flickrphotos.R;

import java.util.List;

public class MainFragment extends Fragment implements DataView, PhotoDataAdapter.OnPhotoClickListener {

    private RecyclerView recyclerView;
    private DataController dataController;
    private Button button;
    private EditText inputText;
    private CallbackListener listener;
    private PhotoDataAdapter photoDataAdapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CallbackListener) {
            listener = (CallbackListener) context;
        } else {
            throw new ClassCastException(context + " must implement CallbackListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        dataController = new DataController(this);
        button = rootView.findViewById(R.id.button);
        inputText = rootView.findViewById(R.id.inputText);

        if(photoDataAdapter != null){
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            recyclerView.setAdapter(photoDataAdapter);
        }

        setListeners();
        return rootView;
    }

    private void setListeners(){
        button.setOnClickListener(v -> {
            String textToSearch = inputText.getText().toString();
            if(textToSearch.isEmpty()){
                Toast.makeText(requireContext(), "Please insert a text for search...", Toast.LENGTH_LONG).show();
            }else{
                dataController.fetchData(textToSearch);
            }
            hideKeyboard(requireActivity());
        });

        inputText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                button.performClick();
                hideKeyboard(requireActivity());
                return true;
            }
            return false;
        });
    }

    @Override
    public void displayData(ApiResponse.Photos data) {
        try {
            List<ApiResponse.Photo> photoList = data.getPhoto();
            if (photoList != null) {
                recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
                photoDataAdapter = new PhotoDataAdapter(requireContext(), photoList, this);
                recyclerView.setAdapter(photoDataAdapter);
            }
        }catch(Exception e){
            Toast.makeText(requireContext(), "Error in retrieving resources, check text for search", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void displayError(String error) {
        Toast.makeText(requireContext(), "Error calling service: " + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPhotoClick(ApiResponse.Photo photo) {
        listener.openPhoto(photo);
    }

    public interface CallbackListener{
        void openPhoto(ApiResponse.Photo photo);
    }

    private void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
