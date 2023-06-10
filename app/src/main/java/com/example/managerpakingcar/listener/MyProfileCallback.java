package com.example.managerpakingcar.listener;

import com.example.managerpakingcar.model.response.MyProfileResponseModel;

public interface MyProfileCallback {
    void onLoading();
    void onSuccess(MyProfileResponseModel myProfileResponseModel);
    void onFailure(String errorMessage);
}
