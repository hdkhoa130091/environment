#include <Arduino.h>
#include <DHT.h>
#include <HTTPClient.h>

const char *ssid_sta = "IOT";
const char *password_sta = "12345678";
const String firebaseHost = "https://environmental-monitoring-452b4-default-rtdb.firebaseio.com";
const String firebaseAuth = "Y8jcFSsWVD5UwGpAt5rQm4O0y2vGINHyBwoJECbz";

// ------------------ DHT22 ------------------
#define DHTPIN 4
#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

// ------------------ MQ135 ------------------
const int MQ135_PIN = 32; // Chan tin hieu analog MQ135
const float Vcc = 3.3;
const float RL = 10.0; // dien tro tai 10K ohm
const float R0 = 3.6;  // Gia tri Rs trong khong khi sach (can chinh ly)

// ------------------ GP2Y1010 ------------------
const int dustPin = 34; // Chan tin hieu analog tu cam bien bui
const int ledDust = 25; // Chan dieu khien LED hong ngoai cam bien bui

int samplingTime = 280;
int deltaTime = 40;
int sleepTime = 9680;

void sendToFirebase(String node, String jsonData);

void setup()
{
    Serial.begin(9600);
    dht.begin();
    pinMode(ledDust, OUTPUT);
    digitalWrite(ledDust, HIGH); // Tat LED cam bien bui luc khoi dong

    WiFi.begin(ssid_sta, password_sta);
    Serial.println("Connecting to WiFi...");

    while (WiFi.status() != WL_CONNECTED)
    {
        delay(1000);
        Serial.println("Connecting...");
    }

    Serial.println("Connected to STA WiFi!");
    Serial.print("STA IP Address: ");
    Serial.println(WiFi.localIP());
}

void loop()
{
    Serial.println("======== DOC DU LIEU ========");

    // ----------- DHT22 -----------
    float h = dht.readHumidity();
    float t = dht.readTemperature();
    float f = dht.readTemperature(true);
    float hic = dht.computeHeatIndex(t, h, false);

    if (isnan(h) || isnan(t))
    {
        Serial.println("Khong doc duoc cam bien DHT22");
    }
    else
    {
        Serial.print("Nhiet do: ");
        Serial.print(t);
        Serial.print(" *C | Do am: ");
        Serial.print(h);
        Serial.print(" % | Chi so HI: ");
        Serial.print(hic);
        Serial.println(" *C");
    }

    // ----------- MQ135 -----------
    int adcValue = analogRead(MQ135_PIN);
    float voltage = adcValue * (Vcc / 4095.0);
    float Rs = (Vcc - voltage) * RL / voltage;
    float ratio = Rs / R0;

    float A = -0.42, B = 1.92;
    float ppm = pow(10, A * log10(ratio) + B);
    if (ppm < 0)
        ppm = 0;

    Serial.print("MQ135 - CO2: ");
    Serial.print(ppm, 1);
    Serial.println(" ppm");

    // ----------- GP2Y1010 -----------
    digitalWrite(ledDust, LOW);
    delayMicroseconds(samplingTime);
    int rawDust = analogRead(dustPin);
    delayMicroseconds(deltaTime);
    digitalWrite(ledDust, HIGH);
    delayMicroseconds(sleepTime);

    float dustVoltage = rawDust * (3.3 / 4095.0); // Neu cam bien dung Vcc 3.3V
    float dustDensity = 0.17 * dustVoltage - 0.1; // mg/m3
    if (dustDensity < 0)
        dustDensity = 0;

    Serial.print("GP2Y1010 - Raw: ");
    Serial.print(rawDust);
    Serial.print(" | Dien ap: ");
    Serial.print(dustVoltage, 3);
    Serial.print(" V | Mat do bui: ");
    Serial.print(dustDensity, 3);
    Serial.println(" mg/m3");

    Serial.println("");

    String path = "/Node_1";
    String jsonData = "{";
    jsonData += "\"dust_density\":" + String(dustDensity, 2) + ",";
    jsonData += "\"humidity\":" + String(h, 2) + ",";
    jsonData += "\"mq135\":" + String(ppm, 2) + ",";
    jsonData += "\"temperature\":" + String(t, 2);
    jsonData += "}";
    sendToFirebase(path, jsonData);
}

void sendToFirebase(String node, String jsonData)
{
    if (WiFi.status() == WL_CONNECTED)
    {
        HTTPClient http;

        String url = firebaseHost + node + ".json?auth=" + firebaseAuth;
        http.begin(url);
        http.addHeader("Content-Type", "application/json");

        int httpResponseCode = http.PATCH(jsonData);

        if (httpResponseCode > 0)
        {
            String response = http.getString();
            Serial.println("HTTP Response code (PATCH): " + String(httpResponseCode));
            Serial.println("Response: " + response);
        }
        else
        {
            Serial.println("Error on sending PATCH: " + String(httpResponseCode));
        }

        http.end();
    }
    else
    {
        Serial.println("WiFi not connected");
    }
}