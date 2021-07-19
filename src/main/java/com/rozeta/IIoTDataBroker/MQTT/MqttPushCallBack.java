package com.rozeta.IIoTDataBroker.MQTT;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname PushCallback
 * @Description Consumer monitoring
 * @Date 2019/4/11 23:31
 * @Created by Jack
 */

@Component
public class MqttPushCallBack implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    @Autowired
    private MqttConfig mqttConfig;

    private static MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        // After the connection is lost, it is usually reconnected here
        logger.info("Disconnected, can be reconnected");
        if (null != client) {
            mqttConfig.getMqttPushClient();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // The message you get after you subscribe will be executed here

        String message = null;
        message = new String(mqttMessage.getPayload());

        logger.info("Receive message subject : " + topic);
        logger.info("receive messages Qos : " + mqttMessage.getQos());
        logger.info("Receive message content : " + message);

        MqttLogpresso mqttLogpresso = new MqttLogpresso();
        mqttLogpresso.insert(topic, mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}