package com.example.environmental_monitoring;

public class Data {
    public float temperature, humidity, mq135, dust_density;

    public Data() {
    }

    public Data( float temperature, float humidity, float mq135, float dust_density) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.mq135 = mq135;
        this.dust_density = dust_density;
    }
}