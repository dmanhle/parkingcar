package com.example.managerpakingcar.model.request;

import com.google.gson.annotations.SerializedName;

public class SignUpRequestModel {
//"email": "xomconbl@gmail.com",
//        "first_name": "Test 1",
//        "is_vehicle_owner": true,
//        "last_name": "Test 2",
//        "password": "secret123",
//        "phone_number": "0967224456",
//        "plate_number": "74D1-21167",
//        "register_date": "29/11/2021 12:12:12",
//        "user_name": "user_name",
//        "vehicle_Model": "serious",
//        "vehicle_brand": "yamahar"

    @SerializedName("user_name")
    private String userName;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("vehicle_Model")
    private String vehicleModel;

    @SerializedName("vehicle_brand")
    private String vehicleBrand;

    @SerializedName("plate_number")
    private String plateNumber;

    @SerializedName("register_date")
    private String registerDate; // 29/11/2021

    @SerializedName("email")
    private String email;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("password")
    private String password;

    @SerializedName("is_vehicle_owner")
    private boolean vehicleOwner;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(boolean vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }
}
