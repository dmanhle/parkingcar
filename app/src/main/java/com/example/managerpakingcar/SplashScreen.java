package com.example.managerpakingcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.managerpakingcar.api.MyProfileDao;
import com.example.managerpakingcar.data.AppDatabase;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //        Check whether in database havent data yet ?
        MyProfileDao myProfileDao = instanceDatabase().myProfileDao();
        if(myProfileDao.getAllListMyProfile().size() > 0){
           navigationToMainActivity();
        }else{
           navigationToSignUp();
        }
    }

    private AppDatabase instanceDatabase(){
        return Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    private void navigationToMainActivity(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
    private void navigationToSignUp(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
}