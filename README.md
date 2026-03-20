# Home Environment Monitoring System

An integrated IoT solution designed to monitor indoor air quality and environmental conditions in real-time. The system acquires data from multiple sensors (**DHT22**, **MQ135**, **GP2Y1010AU0F**), synchronizes it with **Firebase**, and provides a live dashboard on a mobile application.

---
![1](https://github.com/user-attachments/assets/2645f078-f6ef-44d7-97ca-0487a917076e)
<img width="540" height="687" alt="2" src="https://github.com/user-attachments/assets/814338c7-232b-4c0d-a7e0-a8c5635cc0aa" />
<img width="540" height="591" alt="3" src="https://github.com/user-attachments/assets/ab63d605-4511-41dd-94b3-0bcedd419304" />

---

## Features
* **Multi-Sensor Fusion:** Real-time monitoring of temperature, humidity, air quality, and dust density.
* **Air Quality Analysis:** Precise measurement of harmful gases and PM2.5 dust particles.
* **Cloud Integration:** Instant data transmission to **Firebase Realtime Database**.
* **Mobile Dashboard:** User-friendly interface for remote environmental tracking.

---

## System 
1. **Hardware:** **ESP32** microcontroller interfacing with environmental sensors.
2. **Cloud:** **Firebase** managing real-time data streaming and secure storage.
3. **Application:** Mobile application built with a robust Android-based framework.

---

## Firebase Cloud Authorization & Security
To protect user data, the system implements **Firebase Cloud Authorization**:

* **Identity & Access Management (IAM):** Restricting access to authorized users via **Firebase Authentication**.
* **Security Rules:** Database rules enforced to ensure only authenticated users can read/write their specific environmental data.
* **Service Account Authorization:** Secure communication between the ESP32 and Firebase using unique project credentials.

---

## Tech Stack

| Component | Technology / Tools |
| :--- | :--- |
| **Microcontroller** | **ESP32** (Wi-Fi enabled) |
| **Sensors** | **DHT22**, **MQ135**, **GP2Y1010AU0F** |
| **Backend** | **Firebase** (Realtime DB & Auth) |
| **Mobile App** | Android / Flutter |
| **Build System** | Gradle |
| **Protocol** | HTTP / Wifi |

---

## Test
Pre-built debug APK in the following path:
`app/build/outputs/apk/androidTest/debug/app-debug-android...apk`
