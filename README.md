# 🚗 VehicleApp

## 🧩 Overview
A **Java console application** that collects details for three vehicle types — **Car**, **Motorcycle**, and **Truck** — then prints a typed summary for each.  
The design showcases clean **OOP with interfaces**, **final immutable classes**, **enums**, and **polymorphism** (dynamic `toString()`), along with robust **input validation** utilities.

---

## ⚙️ Features
- Guided prompts to enter:
  - Car: make, model, year, doors, fuel type
  - Motorcycle: make, model, year, wheels, motorcycle type
  - Truck: make, model, year, cargo capacity (tons), transmission type
- Strong input validation with reprompting:
  - Non-empty strings
  - Ranged integers/doubles (e.g., year 1990–2035, capacity 0.5–40.0)
  - Enum selection with forgiving input (`sport`, `Sport`, `sport bike` → `SPORT`)
- Clear summary output leveraging **polymorphic** `toString()`

---

## 🧠 Concepts Used

| Concept | Where / How |
|---|---|
| **Interfaces** (`Vehicle`, `CarVehicle`, `MotorVehicle`, `TruckVehicle`) | Define role-specific contracts for each type |
| **Enums** (`FuelType`, `MotoType`, `TransmissionType`) | Type-safe categorical inputs |
| **Final immutable classes** (`Car`, `Motorcycle`, `Truck`) | All fields `private final`; values set in constructor only |
| **Polymorphism** | `List<Vehicle>` holds mixed types; dynamic `toString()` at print time |
| **Generics** | `List<Vehicle>`, generic `readEnum(Class<E>)` with `<E extends Enum<E>>` |
| **Collections** | `ArrayList` stores a heterogeneous fleet |
| **Input validation** | `readNonEmpty`, `readInt`, `readDouble`, `readEnum` with loops and error messages |
| **Exception handling** | Guarded main flow with `try/catch` and fail-fast behavior |
| **I/O** | Console I/O via `Scanner` |

---

## ▶️ How to Run

1️⃣ Open a terminal in the project directory.  
2️⃣ Compile:
```bash
javac VehicleApp.java
```
3️⃣ Run:
```bash
java VehicleApp
```

## 🏫 Educational Context

Built for a CS assignment to demonstrate interfaces, enums, immutability, generics, collections, and input validation in a concise, testable console app.
