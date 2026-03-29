package com.smartsystem.models;

public enum DeviceStatus {
    ONLINE("The device is on."),
    OFFLINE("The device is turned off."),
    ERROR("The device has malfunctioned.");

    private final String status;

    DeviceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
