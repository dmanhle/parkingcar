package com.example.managerpakingcar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.managerpakingcar.R;
import com.example.managerpakingcar.data.AppDatabase;
import com.example.managerpakingcar.model.local.MyProfile;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ImageSlider imageSlider;
    private View view;
    private TextView txtNameWellCome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//      Your code
        initSetupSlideImage();
//      End code;
        if(instanceDatabase().myProfileDao().getAllListMyProfile().size() > 0){
            MyProfile myProfile = instanceDatabase().myProfileDao().getAllListMyProfile().get(0);
            txtNameWellCome.setText(myProfile.firstName + myProfile.lastName);
        }
        return view;
    }
    private void initSetupSlideImage() {
        imageSlider = view.findViewById(R.id.imageSlider);
        txtNameWellCome = view.findViewById(R.id.txtYourNameWellcomback);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slide_banner_1, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);
    }
    private AppDatabase instanceDatabase(){
        return Room.databaseBuilder(getContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }
}