package com.smartsystem.contract;

public record PowerCommand(boolean turnOn) implements DeviceCommand {

    public PowerCommand {
        System.out.println("Command created: " + (turnOn ? "ON" : "OFF"));
    }

    @Override
    public String getAction() {
        return turnOn ? "Power ON..." : "Power OFF...";
    }


}
