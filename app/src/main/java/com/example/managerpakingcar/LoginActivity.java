package com.example.managerpakingcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.managerpakingcar.api.ApiService;
import com.example.managerpakingcar.api.MyProfileDao;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.data.AppDatabase;
import com.example.managerpakingcar.listener.LoginCallback;
import com.example.managerpakingcar.model.request.SignInRequestModel;
import com.example.managerpakingcar.model.response.BaseResponseModel;
import com.example.managerpakingcar.model.response.MyProfileResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        initView();
        btnLogin.setOnClickListener(view -> {
            hideKeyboard();
            postListUsers(
                    editUserName.getText().toString().trim(),
                    editPassword.getText().toString().trim(),
                    new LoginCallback() {
                        @Override
                        public void onLoading() {
                            progressBar.setVisibility(View.VISIBLE);
                            linearLayout.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onSucess(String token) {
                            progressBar.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.INVISIBLE);
                            Constant.TOKEN = token;
                            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("data", token));
                            finish();
                        }

                        @Override
                        public void onFailure(String message) {
                            progressBar.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        });
    }
    private void initView(){
        editUserName = findViewById(R.id.editTextUserName);
        editPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.cirLoginButton);
        progressBar = findViewById(R.id.progresslogin);
        progressBar.setVisibility(View.INVISIBLE);
        linearLayout = findViewById(R.id.overlayLayoutLogin);

        // xử lí overlay, khi đang đăng nhập mà người dùng chờ lâu quá, họ bấm vào màn hình thì minh finsh cái activity hiện tại
        // load lại activity đang đăng nhập này


    }
    private void postListUsers(String userName, String password, LoginCallback loginCallback) {
        SignInRequestModel userRequestModel = new SignInRequestModel();
        userRequestModel.setUserName(userName);
        userRequestModel.setPassword(password);
        loginCallback.onLoading();
        ApiService.apiService.signIn(userRequestModel).enqueue(new Callback<BaseResponseModel<String>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<String>> call, Response<BaseResponseModel<String>> response) {
                BaseResponseModel<String> stringBaseResponseModel = response.body();
                if (stringBaseResponseModel != null) {
                    Log.d("AAA",response.body().toString());
                    String token = stringBaseResponseModel.getData();
                    loginCallback.onSucess(token);
                }else {
                    loginCallback.onFailure("Sai tài khoản hoặc mật khẩu");
                }
            }
            @Override
            public void onFailure(Call<BaseResponseModel<String>> call, Throwable t) {
                loginCallback.onFailure(t.getMessage());
            }
        });
    }


    public void onLoginClick(View View) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
