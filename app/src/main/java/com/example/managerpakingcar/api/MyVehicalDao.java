package com.example.managerpakingcar.api;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.managerpakingcar.model.local.MyProfile;
import com.example.managerpakingcar.model.local.MyVehical;

import java.util.List;

@Dao
public interface MyVehicalDao {
    @Insert
    void insertAll(MyVehical myProfile);

    @Delete
    void delete(MyVehical myProfile);

    @Query("delete from myvehical")
    void deleteAll();

    @Query("SELECT * FROM myvehical")
    List<MyVehical> getAllListMyVehicals();
}
