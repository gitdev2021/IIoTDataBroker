package com.rozeta.IIoTDataBroker.MQTT;

public class MqttClientSetting {
    public static final String   MQTT_SERVER   = "tcp://127.0.0.1:1883";
    public static final String   MQTT_CLIENT   = "client1";
    public static final String   MQTT_USERNAME = "admin";
    public static final String   MQTT_PASSWORD = "password";
    public static final String[] MQTT_TOPIC    = { "IoTDevice", "DataPARC", "test" };
    public static final int[]    MQTT_QOS      = { 1, 1, 1 };
    public static final int      TIMEOUT       = 1000;
    public static final int      KEEP_ALIVE    = 1000;
    // public static final String TOPIC        = "IoTDevice";
    // public static final int QOS             = 1;
}
