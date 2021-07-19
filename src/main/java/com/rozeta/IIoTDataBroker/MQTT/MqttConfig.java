package com.rozeta.IIoTDataBroker.MQTT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

import static com.rozeta.IIoTDataBroker.MQTT.MqttClientSetting.*;

/**
 * @Classname MqttConfig
 * @Description mqtt Related configuration information
 * @Date 2020/3/5 11:00
 * @Created by
 */

@Component
@Setter
@Getter
public class MqttConfig {

    @Autowired
    private MqttPushClient mqttPushClient;

    @Bean
    public MqttPushClient getMqttPushClient() {
        System.out.println("MQTT_SERVER: "    + MQTT_SERVER);
        System.out.println("MQTT_CLIENT: "    + MQTT_CLIENT);
        System.out.println("MQTT_USERNAME: "  + MQTT_USERNAME);
        System.out.println("MQTT_PASSWORD: "  + MQTT_PASSWORD);
        System.out.println("MQTT_TOPIC: "     + MQTT_TOPIC);
        System.out.println("MQTT_QOS: "       + MQTT_QOS);
        System.out.println("TIMEOUT: "        + TIMEOUT);
        System.out.println("KEEP_ALIVE: "     + KEEP_ALIVE);
        mqttPushClient.connect(MQTT_SERVER, MQTT_CLIENT, MQTT_USERNAME, MQTT_PASSWORD, TIMEOUT, KEEP_ALIVE);

        // Subscribe to all topics starting with test
        mqttPushClient.subscribe(MQTT_TOPIC, MQTT_QOS);

        return mqttPushClient;
    }
}

