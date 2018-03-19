package com.example.user.parsejsonwithretrofit.model;

import java.util.List;

/**
 * Created by User on 03/19/2018.
 */

public class Device {
    private String sessionId;
    private String timezone;
    private Integer updateTime;
    private List<ParameterObject> objects;

    public Device() {
    }

    public Device(String sessionId, String timeZone, Integer updateTime, List<ParameterObject> objects) {
        this.sessionId = sessionId;
        this.timezone = timeZone;
        this.updateTime = updateTime;
        this.objects = objects;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTimeZone() {
        return timezone;
    }

    public void setTimeZone(String timeZone) {
        this.timezone = timeZone;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public List<ParameterObject> getObjects() {
        return objects;
    }

    public void setObjects(List<ParameterObject> objects) {
        this.objects = objects;
    }
}
