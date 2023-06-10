package com.example.managerpakingcar.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.managerpakingcar.LoginActivity;
import com.example.managerpakingcar.MainActivity;
import com.example.managerpakingcar.R;
import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.model.response.BaseResponseModel;
import com.example.managerpakingcar.model.response.CheckingResponseModel;
import com.example.managerpakingcar.qrApi.CaptureAct;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanrFragment extends Fragment {

    private View view;
    private MeowBottomNavigation navigation;
    private Button button;

    public ScanrFragment(MeowBottomNavigation navigation) {
        this.navigation = navigation;
    }


    public ScanrFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scanr, container, false);

//      Your code begin
        button = view.findViewById(R.id.btnScanner);
        button.setOnClickListener(view -> {
            scanCode();
        });
//      Your code end;
        return view;
    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            callChecking(result.getContents());
        }
    });
    private void callChecking(String parkingAreaId) {
        ApiService.apiService.checking(Constant.TOKEN, parkingAreaId).enqueue(new Callback<BaseResponseModel<CheckingResponseModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<CheckingResponseModel>> call, Response<BaseResponseModel<CheckingResponseModel>> response) {
                BaseResponseModel<CheckingResponseModel> stringBaseResponseModel = response.body();
                if (stringBaseResponseModel.getStatus() == 200) {
                    showCheckingMessage(stringBaseResponseModel.getData().getMessage());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Scan failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResponseModel<CheckingResponseModel>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCheckingMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Checking");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                // come back home page
//                startActivity(new Intent(getActivity(), MainActivity.class));
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new HomeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }).show();
    }
}