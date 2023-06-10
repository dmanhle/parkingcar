package com.example.managerpakingcar.model.response;

import com.example.managerpakingcar.model.local.MyProfile;
import com.example.managerpakingcar.model.local.MyVehical;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MyProfileResponseModel implements Serializable {
    @SerializedName("user_id")
    private String userID;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("role_code")
    private String myRole;

    @SerializedName("vehicles")
    private List<MyVehiclesResponseModel> informationMyVehicle;

    @SerializedName("parking_area")
    private String parkingArea;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

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

    public String getMyRole() {
        return myRole;
    }

    public void setMyRole(String myRole) {
        this.myRole = myRole;
    }

    public List<MyVehiclesResponseModel> getInformationMyVehicle() {
        return informationMyVehicle;
    }

    public void setInformationMyVehicle(List<MyVehiclesResponseModel> informationMyVehicle) {
        this.informationMyVehicle = informationMyVehicle;
    }

    public MyProfileResponseModel(String userID, String userName, String firstName, String lastName, String email, String phoneNumber, String myRole, List<MyVehiclesResponseModel> informationMyVehicle) {
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.myRole = myRole;
        this.informationMyVehicle = informationMyVehicle;
    }
    public MyProfile toMyProfileLocal(String keyAuthorize){
        return new MyProfile(
                1,
                keyAuthorize,
                this.userID,
                this.userName,
                this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber,
                this.myRole,
                this.parkingArea
        );
    }
    public MyVehical toMyVehiclesLocal(){
        return informationMyVehicle.get(0).toMyVehical();
    }
}
