package com.example.user.parsejsonwithretrofit.model;

/**
 * Created by User on 03/19/2018.
 */

public class AddressParameter {
    private String address;
    private String dataType;
    private int length;

    public AddressParameter() {
    }

    public AddressParameter(String address, String dataType, int length) {
        this.address = address;
        this.dataType = dataType;
        this.length = length;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
