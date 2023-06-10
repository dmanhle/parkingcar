package com.example.managerpakingcar.model.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyVehical {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "vehicle_id")
    public String userID;

    @ColumnInfo(name = "plate_number")
    public String plateNumber;

    @ColumnInfo(name = "vehicle_model")
    public String vehicleModel;

    @ColumnInfo(name = "vehicle_brand")
    public String vehicleBrand;

    @ColumnInfo(name = "register_date")
    public String registerDate;

    @ColumnInfo(name = "username_owner")
    public String userNameOwn;

    @ColumnInfo(name = "register_parking_date")
    public String registerParkingDate;

    @ColumnInfo(name = "accepted_parking_date")
    public String isAceptedParkingDate;

    public MyVehical(int uid, String userID, String plateNumber, String vehicleModel, String vehicleBrand, String registerDate, String userNameOwn, String registerParkingDate, String isAceptedParkingDate) {
        this.uid = uid;
        this.userID = userID;
        this.plateNumber = plateNumber;
        this.vehicleModel = vehicleModel;
        this.vehicleBrand = vehicleBrand;
        this.registerDate = registerDate;
        this.userNameOwn = userNameOwn;
        this.registerParkingDate = registerParkingDate;
        this.isAceptedParkingDate = isAceptedParkingDate;
    }

    public int getUid() {
        return uid;
    }

    public String getUserID() {
        return userID;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getRegisterParkingDate() {
        return registerParkingDate;
    }

    public String getIsAceptedParkingDate() {
        return isAceptedParkingDate;
    }
}
