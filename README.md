# Home Environment Monitoring System

An integrated IoT solution designed to monitor indoor air quality and environmental conditions in real-time. The system acquires data from multiple sensors (**DHT22**, **MQ135**, **GP2Y1010AU0F**), synchronizes it with **Firebase**, and provides a live dashboard on a mobile application.

---
![1](https://github.com/user-attachments/assets/d123f17b-fa4a-4643-a021-3efb2f48db5e)
<img width="320" height="407" alt="2" src="https://github.com/user-attachments/assets/95d5ae49-34ff-4b1d-a523-39910200faaf" />

<img width="844" height="1881" alt="image" src="https://github.com/user-attachments/assets/2194fd94-c9ff-4779-8de0-b3447c74e50a" />


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
