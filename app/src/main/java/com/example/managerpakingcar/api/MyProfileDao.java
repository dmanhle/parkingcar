package com.example.managerpakingcar.api;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.managerpakingcar.model.local.MyProfile;
import com.example.managerpakingcar.model.local.MyVehical;

import java.util.List;

@Dao
public interface MyProfileDao {
    @Insert
    void insertAll(MyProfile myProfile);

    @Delete
    void delete(MyProfile myProfile);

    @Query("delete from myprofile")
    void deleteAll();

    @Query("SELECT * FROM myprofile")
    List<MyProfile> getAllListMyProfile();

}
