package com.smartsystem.repository;

import com.smartsystem.models.SmartDevice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class DeviceRepository {
    private final HashMap<String, SmartDevice> devices = new HashMap<>();

    public void save(SmartDevice device) {
        if (devices.containsKey(device.getMacAddress())) {
            System.out.println("Device already registered!");
        }
        devices.put(device.getMacAddress(), device);
    }

    public Optional<SmartDevice> findDeviceByMac(String mac) {
        return Optional.ofNullable(devices.get(mac));
    }

    public Collection<SmartDevice> findAll() {
        if (devices.isEmpty()) {
            System.out.println("No devices found.");
        }
        return devices.values();
    }

    public void turnOffDevice(String roomName) {
        devices.values().stream()
                .filter(device -> device.getInfo().room().equalsIgnoreCase(roomName))
                .forEach(device -> device.setActive(false));

        System.out.println("All devices in " + roomName + " are all turned off.");
    }

    public void turnOnDevice(String roomName) {
        devices.values().stream()
                .filter(device -> device.getInfo().room().equalsIgnoreCase(roomName))
                .forEach(device -> device.setActive(true));
        System.out.println("All devices in " + roomName + " are all turned on.");
    }

    public void assignDevice(String mac, String newRoom) {
        findDeviceByMac(mac).ifPresentOrElse(device -> {
            device.assignRoom(newRoom);
            System.out.println("Device " + mac + " assigned to " + newRoom);
        }, () -> {
            System.out.println("ERROR: Device not found!");
        });
    }

    public void generateReport() {
        System.out.println("\n----SUMMARY OF REPORT----");

        devices.values().forEach(System.out::println);
        System.out.println("----NOTHING FOLLOWS----\n");
    }

    public void relocate(String mac, String newRoom) {
        findDeviceByMac(mac).ifPresentOrElse(device -> {
            device.assignRoom(newRoom);
            System.out.println("Device reassigned to " + newRoom);
        }, () -> System.out.println("ERROR: Device not found!"));
    }

    public void removeDevice(String mac) {
        findDeviceByMac(mac).ifPresentOrElse(device -> {
            devices.remove(mac);
            System.out.println("Device removed successfully!");
        }, () -> System.out.println("ERROR: Device not found."));
    }

}
