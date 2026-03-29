package com.smartsystem.models;

import com.smartsystem.exceptions.InvalidDataException;

import java.util.Objects;
import java.util.regex.Pattern;

public record DeviceInfo(String macAddress, String model, String room) {
    private static final Pattern MAC_ADDRESS_FORMAT = Pattern.compile(
            "^((?:[0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}|(?:[0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4})$"
    );

    public DeviceInfo {
        Objects.requireNonNull(macAddress, "Your MAC address cannot be null!");
        Objects.requireNonNull(model, "The model of your device cannot be null!");
        Objects.requireNonNull(room, "Your room cannot be null!");

        if (macAddress.isBlank()) {
            throw new InvalidDataException("Your MAC Address is required!");
        }

        if (model.isBlank()) {
            throw new InvalidDataException("The model of your device is required!");
        }

        if (room.isBlank()) {
            throw new InvalidDataException("Your room is required!");
        }

        if (!MAC_ADDRESS_FORMAT.matcher(macAddress).matches()) {
            throw new InvalidDataException("Invalid MAC Address format!");
        }
    }
}
