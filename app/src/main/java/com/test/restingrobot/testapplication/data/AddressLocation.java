package com.test.restingrobot.testapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jon Lange, 6/11/18
 */

public class AddressLocation {

    @SerializedName("lat")
    private double Latitude;

    @SerializedName("lng")
    private double Longitude;

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(long latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(long longitude) {
        Longitude = longitude;
    }
}
