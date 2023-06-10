package com.example.managerpakingcar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.managerpakingcar.LoginActivity;
import com.example.managerpakingcar.MainActivity;
import com.example.managerpakingcar.R;
import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.data.AppDatabase;
import com.example.managerpakingcar.model.local.MyProfile;
import com.example.managerpakingcar.model.local.MyVehical;
import com.example.managerpakingcar.model.response.BaseResponseModel;
import com.example.managerpakingcar.model.response.MyProfileResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    private MyProfileResponseModel myProfileResponseModel;
    private View view;
    private TextView txtName;
    private TextView txtEmail;
    private TextView userName;
    private TextView phone;
    private TextView vehiclesModel;
    private TextView plateNumber;
    private TextView registerDate;
    private Button btnLogout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        MyProfile myProfile = instanceDatabase().myProfileDao().getAllListMyProfile().get(0);
        MyVehical myVehical = instanceDatabase().myVehicalDao().getAllListMyVehicals().get(0);
        txtName.setText(myProfile.firstName + myProfile.lastName);
        txtEmail.setText(myProfile.email);
        userName.setText(myProfile.userName);
        phone.setText(myProfile.phoneNumber);
        vehiclesModel.setText(myVehical.vehicleModel);
        plateNumber.setText(myVehical.plateNumber);
        registerDate.setText(myVehical.registerDate);
        btnLogout.setOnClickListener(view1 -> {
              instanceDatabase().myProfileDao().deleteAll();
              instanceDatabase().myVehicalDao().deleteAll();
              Intent intent = new Intent(getActivity(), LoginActivity.class);
              startActivity(intent);
           }
        );
        return view;
    }
    private void init(View view){
        txtName = view.findViewById(R.id.name);
        txtEmail = view.findViewById(R.id.email);
        userName = view.findViewById(R.id.username_profile_txt);
        phone = view.findViewById(R.id.mobilePhone_profile_txt);
        vehiclesModel = view.findViewById(R.id.verhicleModel_profile_txt);
        plateNumber = view.findViewById(R.id.plateNumber_profile_txt);
        registerDate = view.findViewById(R.id.registerDate_profile_txt);
        btnLogout = view.findViewById(R.id.button2);
    }
    private AppDatabase instanceDatabase(){
        return Room.databaseBuilder(getContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }
}