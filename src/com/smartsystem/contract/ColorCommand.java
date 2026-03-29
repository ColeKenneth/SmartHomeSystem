package com.smartsystem.contract;

import com.smartsystem.exceptions.InvalidCommandException;

import java.util.Objects;
import java.util.regex.Pattern;

public record ColorCommand(String hexCode) implements DeviceCommand {
    private static final Pattern HEX_CODE = Pattern.compile("^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");

    public ColorCommand {
        Objects.requireNonNull(hexCode, "Hex code cannot be null!");
        if (!HEX_CODE.matcher(hexCode).matches()) {
            throw new InvalidCommandException("Invalid hex code format! \nSAMPLE FORMAT: #FFFDD0");
        }
    }

    @Override
    public String getAction() {
        return "Color set to: " + hexCode;
    }
}
