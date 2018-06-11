package com.test.restingrobot.testapplication.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jon Lange, 6/11/18
 */

public class Address {

    @SerializedName("street")
    private String street;

    @SerializedName("suite")
    private String suite;

    @SerializedName("city")
    private String city;

    @SerializedName("zipcode")
    private String zipCode;

    @SerializedName("geo")
    private AddressLocation location;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AddressLocation getLocation() {
        return location;
    }

    public void setLocation(AddressLocation location) {
        this.location = location;
    }
}
