package com.smartsystem.models;

import java.util.Objects;

public class SmartDevice {
    private DeviceInfo info;
    private DeviceStatus deviceStatus;
    private boolean active;
    private int brightness = 100;
    private String hexCode = "#FFFFFF";


    public SmartDevice(DeviceInfo info, DeviceStatus deviceStatus, boolean active, int brightness, String hexCode) {
        this.info = Objects.requireNonNull(info, "Device information cannot be null!");
        this.deviceStatus = Objects.requireNonNull(deviceStatus, "Device status cannot be null!");
        this.active = active;
        this.brightness = brightness;
        this.hexCode = Objects.requireNonNull(hexCode, "Hex code cannot be null!");

    }

    public String getMacAddress() {
        return info.macAddress();
    }

    public String getRoom() {
        return info.room();
    }

    public String getModel() {
        return info.model();
    }

    public void assignRoom(String newRoom) {
        this.info = new DeviceInfo(info.macAddress(), info.model(), newRoom);
    }

    public DeviceInfo getInfo() {
        return info;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus status) {
        this.deviceStatus = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = Objects.requireNonNull(hexCode, "Hex code cannot be null!");
    }

    @Override
    public String toString() {
        return """
                ==========================================
                        SMART DEVICE INFORMATION
                ==========================================
                Model:       %s
                MAC Address: %s
                Location:    %s
                Status:      %s
                Power:       %s
                Brightness:  %d%%
                Hex Color:   %s
                ==========================================\s
               \s""".formatted(
                        info.model(), info.macAddress(), info.room(), deviceStatus,
                active ? "ON" : "OFF", brightness, hexCode
        );
    }
}
