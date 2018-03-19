package com.example.user.parsejsonwithretrofit.model;

import java.util.List;

/**
 * Created by User on 03/19/2018.
 */

public class ParameterObject {
    private String hostname;
    private List<AddressParameter> addresses;

    public ParameterObject() {
    }

    public ParameterObject(String hostName, List<AddressParameter> addressParameterList) {
        this.hostname = hostName;
        this.addresses = addressParameterList;
    }

    public String getHostName() {
        return hostname;
    }

    public void setHostName(String hostName) {
        this.hostname = hostName;
    }

    public List<AddressParameter> getAddressParameterList() {
        return addresses;
    }

    public void setAddressParameterList(List<AddressParameter> addressParameterList) {
        this.addresses = addressParameterList;
    }
}
