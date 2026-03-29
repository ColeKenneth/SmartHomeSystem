##  SMART HOME SYSTEM
A Java console application that demonstrates **Data-Oriented Programming (DOP)**
concepts introduced in modern Java, including records, sealed interfaces, enums,
pattern matching, Optional, and the Stream API.

## FEATURES 
- Register and manage smart devices
- Issue commands (Power, Brightness, Color) via sealed interface + pattern matching
- Control devices by room (turn on/off bulk)
- Assign and relocate devices to different rooms
- Remove devices
- Generate a full device report

## Java Concepts Applied

| Concept | Usage |
|---|---|
| `record` | `DeviceInfo`, `PowerCommand`, `DimCommand`, `ColorCommand` |
| `sealed interface` | `DeviceCommand` — restricts permitted commands |
| `enum` | `DeviceStatus` — backed enum with description |
| Pattern matching `switch` | `CommandProcessor` — exhaustive command handling |
| `Optional` | `findDeviceByMac()` — explicit null handling |
| Stream API | Room filtering, bulk operations, report generation |
| Custom Exceptions | `InvalidCommandException`, `InvalidDataException` |

## Learning Context

This project was built as part of a self-study journey mastering Java,
progressing from Basics/Fundamentals → OOP → Defensive Programming → Data-Oriented Programming.

**Roadmap:**
```
✅ Java Fundamentals
✅ Object-Oriented Programming
✅ Defensive Programming (Exceptions)
✅ Data-Oriented Programming (this project)
⬜ Functional Programming
⬜ Virtual Threads
⬜ Spring Boot
```
