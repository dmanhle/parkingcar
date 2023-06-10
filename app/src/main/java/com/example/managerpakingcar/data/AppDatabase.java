package com.example.managerpakingcar.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.managerpakingcar.api.MyProfileDao;
import com.example.managerpakingcar.api.MyVehicalDao;
import com.example.managerpakingcar.model.local.MyProfile;
import com.example.managerpakingcar.model.local.MyVehical;

@Database(entities = {MyProfile.class, MyVehical.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyProfileDao myProfileDao();
    public abstract MyVehicalDao myVehicalDao();
}
