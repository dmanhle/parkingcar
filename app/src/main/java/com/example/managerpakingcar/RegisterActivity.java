package com.example.managerpakingcar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.model.request.SignUpRequestModel;
import com.example.managerpakingcar.model.response.BaseResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    private EditText editUserName;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editVehicleModel;
    private EditText editVehicleBrand;
    private EditText editPlateNumber;
    private EditText editRegisterDate;
    private EditText editEmail;
    private EditText editPhoneNumber;
    private EditText editPassword;
    private Button btnRegister;


    //"email": "xomconbl@gmail.com",
    //  "first_name": "Test 1",
    //  "is_vehicle_owner": true,
    //  "last_name": "Test 2",
    //  "password": "secret123",
    //  "phone_number": "0967224456",
    //  "plate_number": "74D1-21167",
    //  "register_date": "29/11/2021 12:12:12",
    //  "user_name": "user_name",
    //  "vehicle_Model": "serious",
    //  "vehicle_brand": "yamahar"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        editUserName = findViewById(R.id.editTextUserName);
        editFirstName = findViewById(R.id.editTextFirstName);
        editLastName = findViewById(R.id.editTextLastName);
        editVehicleModel = findViewById(R.id.editTextVerhicleModel);
        editVehicleBrand = findViewById(R.id.editTextVerhicleBrand);
        editPlateNumber = findViewById(R.id.editTextPlateNumber);
        editRegisterDate = findViewById(R.id.editTextRegisterDate);
        editEmail = findViewById(R.id.editTextEmail);
        editPhoneNumber = findViewById(R.id.editTextMobileNumber);
        editPassword = findViewById(R.id.editTextPassword);
        btnRegister = findViewById(R.id.cirRegisterButton);

        btnRegister.setOnClickListener(view -> {
            SignUpRequestModel signUpRequestModel = new SignUpRequestModel();
            signUpRequestModel.setUserName(editUserName.getText().toString());
            signUpRequestModel.setFirstName(editFirstName.getText().toString());
            signUpRequestModel.setLastName(editLastName.getText().toString());
            signUpRequestModel.setVehicleModel(editVehicleModel.getText().toString());
            signUpRequestModel.setVehicleBrand(editVehicleBrand.getText().toString());
            signUpRequestModel.setPlateNumber(editPlateNumber.getText().toString());
            signUpRequestModel.setRegisterDate(editRegisterDate.getText().toString());
            signUpRequestModel.setEmail(editEmail.getText().toString().trim());
            signUpRequestModel.setPhoneNumber(editPhoneNumber.getText().toString());
            signUpRequestModel.setPassword(editPassword.getText().toString().trim());
            signUpRequestModel.setVehicleOwner(true);
            if (validateSignUp(signUpRequestModel)) {
                postSignUp(signUpRequestModel);
            }
        });
    }

    private boolean validateSignUp(SignUpRequestModel signUpRequestModel) {
        boolean isValid = true;
        if (TextUtils.isEmpty(signUpRequestModel.getUserName())) {
            editUserName.setError("User name is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getFirstName())) {
            editFirstName.setError("First name is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getLastName())) {
            editLastName.setError("Last name is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getVehicleModel())) {
            editVehicleModel.setError("Vehicle model is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getVehicleBrand())) {
            editVehicleBrand.setError("Vehicle brand is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getPlateNumber())) {
            editPlateNumber.setError("Plate number is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getRegisterDate())) {
            editRegisterDate.setError("Register date is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getEmail())) {
            editEmail.setError("Email is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getPhoneNumber())) {
            editPhoneNumber.setError("Phone number is required");
            isValid = false;
        }
        if (TextUtils.isEmpty(signUpRequestModel.getPassword())) {
            editPassword.setError("Password is required");
            isValid = false;
        }
        if (signUpRequestModel.getPassword().length() < 6) {
            editPassword.setError("Password must be >= 6 character");
            isValid = false;
        }
        return isValid;
    }

    private void postSignUp(SignUpRequestModel signUpRequestModel) {
        ApiService.apiService.signUp(signUpRequestModel).enqueue(new Callback<BaseResponseModel<String>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<String>> call, Response<BaseResponseModel<String>> response) {
                BaseResponseModel<String> stringBaseResponseModel = response.body();
                if (stringBaseResponseModel.getStatus() == 200) {
                    Toast.makeText(getApplicationContext(), "Sign up successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sign Up failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel<String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }


}
