package com.example.managerpakingcar.model.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyProfile {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "key_authorize")
    public String keyAuthorize;

    @ColumnInfo(name = "user_id")
    public String userID;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "role_code")
    public String roleCode;

    @ColumnInfo(name = "parking_area")
    public String getParkingArea;

    public MyProfile(int uid, String keyAuthorize, String userID, String userName, String firstName, String lastName, String email, String phoneNumber, String roleCode, String getParkingArea) {
        this.uid = uid;
        this.keyAuthorize = keyAuthorize;
        this.userID = userID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roleCode = roleCode;
        this.getParkingArea = getParkingArea;
    }

    public int getUid() {
        return uid;
    }

    public String getKeyAuthorize() {
        return keyAuthorize;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getGetParkingArea() {
        return getParkingArea;
    }
}
