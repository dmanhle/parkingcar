package com.example.managerpakingcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.api.MyProfileDao;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.data.AppDatabase;
import com.example.managerpakingcar.fragment.HomeFragment;
import com.example.managerpakingcar.fragment.ProfileFragment;
import com.example.managerpakingcar.fragment.ScanrFragment;
import com.example.managerpakingcar.listener.MyProfileCallback;
import com.example.managerpakingcar.model.request.SignInRequestModel;
import com.example.managerpakingcar.model.response.BaseResponseModel;
import com.example.managerpakingcar.model.response.MyProfileResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  MainActivity extends AppCompatActivity {
//    private ZXingScannerView mScannerView;
    private MeowBottomNavigation bottomNavigation;
    private Fragment fragment = null;
    private SignInRequestModel signInRequestModel;
    private MyProfileResponseModel myProfileResponseModel;

    private LinearLayout overlayLinearLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        MyProfileDao myProfileDao = instanceDatabase().myProfileDao();
        initView();
        if(intent.getExtras() != null){
             String token = intent.getStringExtra("data");
             Log.d("AAA", "====> "+ token);
            // Sau khi co token, lay du lieu tu remote api, insert vào room để sau này sử dụng
            if(myProfileDao.getAllListMyProfile().size() == 0){
                 getMyProfileResponseModel(token, new MyProfileCallback() {
                     @Override
                     public void onLoading() {
                         overlayLinearLayout.setVisibility(View.VISIBLE);
                         progressBar.setVisibility(View.VISIBLE);
                     }

                     @Override
                     public void onSuccess(MyProfileResponseModel myProfileResponseModel) {
                         instanceDatabase().myProfileDao().insertAll(myProfileResponseModel.toMyProfileLocal(token));
                         instanceDatabase().myVehicalDao().insertAll(myProfileResponseModel.toMyVehiclesLocal());
                         overlayLinearLayout.setVisibility(View.GONE);
                         progressBar.setVisibility(View.GONE);
                     }
                     @Override
                     public void onFailure(String errorMessage) {
                         overlayLinearLayout.setVisibility(View.GONE);
                         progressBar.setVisibility(View.GONE);
                         Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                     }
                 });
            }
        }
    }

    private void getMyProfileResponseModel(String token,MyProfileCallback myProfileCallback){
        myProfileCallback.onLoading();
        ApiService.apiService.myProfile(token).enqueue(new Callback<BaseResponseModel<MyProfileResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<MyProfileResponseModel>> call, Response<BaseResponseModel<MyProfileResponseModel>> response) {
                BaseResponseModel<MyProfileResponseModel> stringBaseResponseModel = response.body();
                if(stringBaseResponseModel != null){
                    myProfileResponseModel = stringBaseResponseModel.getData();
                    myProfileCallback.onSuccess(myProfileResponseModel);
                }else{
                    myProfileCallback.onFailure("Không load được dữ liệu");
                }
            }
            @Override
            public void onFailure(Call<BaseResponseModel<MyProfileResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage()+"", Toast.LENGTH_SHORT).show();
                myProfileCallback.onFailure(t.getMessage());
            }
        });
    }

    private AppDatabase instanceDatabase(){
        return Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    private void initView() {
        overlayLinearLayout = findViewById(R.id.overlayLayout);
        progressBar = findViewById(R.id.progressloadinginformation);
        progressBar.setVisibility(View.INVISIBLE);
        initSetupBottomNav();
    }
    private void initSetupBottomNav(){
        bottomNavigation = findViewById(R.id.bottomNavigation);
        // init BottomNavigation

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_qr_code));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_profile));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new ScanrFragment();
                        break;
                    case 3 :
                        fragment = new ProfileFragment();
                        break;
                }
                replaceFragment(fragment);
            }
        });

        // set home fragment initially selected;
        setInitFragment();
        bottomNavigation.show(1,true);
    }

    private void setInitFragment() {
        fragment = new HomeFragment();
        replaceFragment(fragment);
    }


    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        mScannerView.startCamera();          // Start camera on resume
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mScannerView.stopCamera();           // Stop camera on pause
//    }
//
//    @Override
//    public void handleResult(Result result) {
//
//        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
//        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
//    }

}
