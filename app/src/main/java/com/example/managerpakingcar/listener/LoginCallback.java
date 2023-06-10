package com.example.managerpakingcar.listener;

public interface LoginCallback {
    void onLoading();
    void onSucess(String token);
    void onFailure(String message);
}
