package com.smartsystem.engine;

import com.smartsystem.contract.ColorCommand;
import com.smartsystem.contract.DeviceCommand;
import com.smartsystem.contract.DimCommand;
import com.smartsystem.contract.PowerCommand;
import com.smartsystem.models.SmartDevice;

public class CommandProcessor {
    public void process(SmartDevice device, DeviceCommand cmd) {
        switch (cmd) {
            case PowerCommand p -> device.setActive(p.turnOn());
            case DimCommand d -> device.setBrightness(d.brightnessLevel());
            case ColorCommand c -> device.setHexCode(c.hexCode());
        }
    }
}
