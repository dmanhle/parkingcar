package com.example.managerpakingcar.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckingResponseModel implements Serializable {

    @SerializedName("parking_id")
    private String parkingId;

    @SerializedName("check_type")
    private String checkType;

    @SerializedName("message")
    private String message;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
