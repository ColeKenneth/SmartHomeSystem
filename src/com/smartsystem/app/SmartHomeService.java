package com.smartsystem.app;

import com.smartsystem.contract.ColorCommand;
import com.smartsystem.contract.DimCommand;
import com.smartsystem.contract.PowerCommand;
import com.smartsystem.engine.CommandProcessor;
import com.smartsystem.exceptions.InvalidCommandException;
import com.smartsystem.exceptions.InvalidDataException;
import com.smartsystem.models.DeviceInfo;
import com.smartsystem.models.DeviceStatus;
import com.smartsystem.models.SmartDevice;
import com.smartsystem.repository.DeviceRepository;

import java.util.Collection;
import java.util.Scanner;

public class SmartHomeService {
    private final DeviceRepository repository = new DeviceRepository();
    private final CommandProcessor processor = new CommandProcessor();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        String choice;

        do {
            showMenu();
            choice = sc.nextLine().trim().toUpperCase();
            try {
                switch (choice) {
                    case "A" -> saveDevice();
                    case "B" -> issueCommand();
                    case "C" -> findAllDevices();
                    case "D" -> turnOffDevices();
                    case "E" -> turnOnDevices();
                    case "F" -> assignRoom();
                    case "G" -> generateReport();
                    case "H" -> relocateDevice();
                    case "I" -> removeDevice();
                    case "X" -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (InvalidCommandException | InvalidDataException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        } while (!choice.equals("X"));
    }

    private void showMenu() {
        System.out.println("\n---SMART HOME TRACKING SYSTEM---");
        System.out.println("""
                        A. Save Device
                        B. Issue Command
                        C. Find All Devices
                        D. Turn Off Devices
                        E. Turn On Devices
                        F. Assign Device
                        G. Generate Report
                        H. Relocate Device
                        I. Remove Device
                        X. Exit
                        """);
        System.out.print("Select what you want to do: ");
    }

    private int getNumber(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String inputValue = sc.nextLine().trim();
                return Integer.parseInt(inputValue);
            } catch (NumberFormatException e) {
                System.out.println("Please input a number.");
            }
        }
    }

    private void saveDevice() {
        System.out.print("Enter MAC Address: ");
        String macAddress = sc.nextLine().trim();

        System.out.print("Enter model: ");
        String model = sc.nextLine().trim();

        System.out.print("Room: ");
        String room = sc.nextLine().trim();

        int brightness = getNumber("Set brightness: ");

        System.out.print("Hex Code (e.g. #FFFDD0): ");
        String hexCode = sc.nextLine().trim();

        DeviceInfo info = new DeviceInfo(macAddress, model, room);
        SmartDevice device = new SmartDevice(info, DeviceStatus.ONLINE, true, brightness, hexCode);

        repository.save(device);
        System.out.println("Device saved successfully!");
    }

    private void issueCommand() {
        System.out.print("Enter MAC Address of device: ");
        String mac = sc.nextLine().trim();

        repository.findDeviceByMac(mac).ifPresentOrElse(device -> {
            System.out.println("""
                1. Power
                2. Brightness
                3. Color
                """);
            int cmd = getNumber("Select command: ");

            switch (cmd) {
                case 1 -> {
                    System.out.print("Turn on? (true/false): ");
                    boolean on = Boolean.parseBoolean(sc.nextLine().trim());
                    processor.process(device, new PowerCommand(on));
                }
                case 2 -> {
                    int level = getNumber("Enter brightness (0-100): ");
                    processor.process(device, new DimCommand(level));
                }
                case 3 -> {
                    System.out.print("Enter hex code: ");
                    String hex = sc.nextLine().trim();
                    processor.process(device, new ColorCommand(hex));
                }
            }
        }, () -> System.out.println("ERROR: Device not found!"));
    }

    private void findAllDevices() {
        Collection<SmartDevice> devices = repository.findAll();

        if (devices.isEmpty()) {
            System.out.println("No devices registered.");
            return;
        }
        System.out.println("LIST OF DEVICES: ");
        devices.forEach(System.out::println);
    }

    private void turnOffDevices() {
        System.out.print("Enter room name: ");
        String roomName = sc.nextLine().trim();

        repository.turnOffDevice(roomName);
    }

    private void turnOnDevices() {
        System.out.print("Enter room name: ");
        String roomName = sc.nextLine().trim();

        repository.turnOnDevice(roomName);
    }

    private void assignRoom() {
        System.out.print("Enter MAC Address: ");
        String mac = sc.nextLine().trim();

        System.out.print("Enter room name: ");
        String newRoom = sc.nextLine().trim();

        repository.assignDevice(mac, newRoom);
    }

    private void generateReport() {
        repository.generateReport();
    }

    private void relocateDevice() {
        System.out.print("Enter MAC Address: ");
        String mac = sc.nextLine().trim();

        System.out.print("Enter new room name: ");
        String newRoom = sc.nextLine().trim();

        repository.relocate(mac, newRoom);
    }

    private void removeDevice() {
        System.out.print("Enter MAC Address: ");
        String mac = sc.nextLine().trim();

        repository.removeDevice(mac);
    }


}
