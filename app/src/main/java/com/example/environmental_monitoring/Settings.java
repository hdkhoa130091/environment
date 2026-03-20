package com.example.environmental_monitoring;

public class Settings {
    public float tMin, tMax, hMin, hMax, mqMax, dustMax;

    public Settings() {
    }

    public Settings(float tMin, float tMax, float hMin, float hMax, float mqMax, float dustMax) {
        this.tMin = tMin;
        this.tMax = tMax;
        this.hMin = hMin;
        this.hMax = hMax;
        this.mqMax = mqMax;
        this.dustMax = dustMax;
    }
}
