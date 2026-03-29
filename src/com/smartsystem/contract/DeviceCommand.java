package com.smartsystem.contract;

public sealed interface DeviceCommand permits PowerCommand, DimCommand, ColorCommand {
    String getAction();
}
