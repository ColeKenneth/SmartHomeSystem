package com.smartsystem.contract;

import com.smartsystem.exceptions.InvalidCommandException;

public record DimCommand(int brightnessLevel) implements DeviceCommand {
    public DimCommand {
        if (brightnessLevel < 0 || brightnessLevel > 100) {
            throw new InvalidCommandException("Brightness level must only be from 0 to 100.");
        }
    }

    @Override
    public String getAction() {
        return "Brightness set to " + brightnessLevel + "%";
    }
}
