package com.example.managerpakingcar.model.response;

import com.example.managerpakingcar.model.local.MyVehical;
import com.google.gson.annotations.SerializedName;

public class MyVehiclesResponseModel {
    @SerializedName("vehicle_id")
    private String vehicleID;

    @SerializedName("plate_number")
    private String plateNumber;

    @SerializedName("vehicle_model")
    private String vehicleModel;

    @SerializedName("vehicle_brand")
    private String vehicleBrand;

    @SerializedName("register_date")
    private String registerDate;

    @SerializedName("username_owner")
    private String userNameOwner;

    @SerializedName("register_parking_date")
    private String dateRegisterParking;

    @SerializedName("accepted_parking_date")
    private String isAcceptedParkingDate;

    public MyVehiclesResponseModel(String vehicleID, String plateNumber, String vehicleModel, String vehicleBrand, String registerDate, String userNameOwner, String dateRegisterParking, String isAcceptedParkingDate) {
        this.vehicleID = vehicleID;
        this.plateNumber = plateNumber;
        this.vehicleModel = vehicleModel;
        this.vehicleBrand = vehicleBrand;
        this.registerDate = registerDate;
        this.userNameOwner = userNameOwner;
        this.dateRegisterParking = dateRegisterParking;
        this.isAcceptedParkingDate = isAcceptedParkingDate;
    }
    public MyVehical toMyVehical(){
        return new MyVehical(
             1,
                this.vehicleID,
                this.plateNumber,
                this.vehicleModel,
                this.vehicleBrand,
                this.registerDate,
                this.userNameOwner,
                this.dateRegisterParking,
                this.isAcceptedParkingDate
        );
    }
    public String getVehicleID() {
        return vehicleID;
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

    public String getUserNameOwner() {
        return userNameOwner;
    }

    public String getDateRegisterParking() {
        return dateRegisterParking;
    }

    public String getIsAcceptedParkingDate() {
        return isAcceptedParkingDate;
    }
}


