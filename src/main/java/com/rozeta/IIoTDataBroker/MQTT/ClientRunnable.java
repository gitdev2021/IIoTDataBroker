///*
//
//package com.rozeta.IIoTDataBroker.MQTT;
//
//import static com.rozeta.IIoTDataBroker.MQTT.MqttClientSetting.*;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.concurrent.ScheduledExecutorService;
//
//*/
///*
//public class ClientRunnable {
//
//    private MqttClient client;
//    private MqttConnectOptions options;
//    private ScheduledExecutorService scheduler;
//
//    private void start() throws Exception{
//        client = new MqttClient(MQTT_SERVER, MQTT_CLIENT, new MemoryPersistence());
//        options = new MqttConnectOptions();
//        options.setCleanSession(true);
//        options.setUserName(MQTT_USERNAME);
//        options.setPassword(MQTT_PASSWORD.toCharArray());
//        options.setConnectionTimeout(500);
//        options.setKeepAliveInterval(500);
//        options.setAutomaticReconnect(true);
//        client.setCallback(new MqttPushCallBack());
//        client.connect(options);
//
//        client.subscribe(MQTT_TOPIC, MQTT_QOS);
//    }
//
//    public  static void main (String[] args) throws Exception{
//        ClientRunnable client = new ClientRunnable();
//        client.start();
//    }
//}
//*//*
//
//
//public class ClientRunnable {
//
//    private MqttConfig mqttConfig;
//    private MqttPushClient mqttPushClient;
//    private MqttClient mqttClient;
//    private MqttTopic mqttTopic;
//    private MqttMessage mqttMessage;
//
//    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);
//
//    private void start() throws Exception {
//
//        mqttClient = new MqttClient(MQTT_SERVER, MQTT_CLIENT, new MemoryPersistence());
//
//        mqttClient.setCallback(new PushCallBack());
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setCleanSession(false);
//        mqttConnectOptions.setUserName(MQTT_USERNAME);
//        mqttConnectOptions.setPassword(MQTT_USERNAME.toCharArray());
//        mqttConnectOptions.setConnectionTimeout(TIMEOUT);
//        mqttConnectOptions.setKeepAliveInterval(KEEP_ALIVE);
//
//        try {
//            mqttClient.setCallback(new PushCallBack());
//            mqttClient.connect(mqttConnectOptions);
//            mqttTopic = mqttClient.getTopic(TOPIC);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//
//        mqttMessage = new MqttMessage();
//        mqttMessage.setQos(QOS);
//        mqttMessage.setRetained(false);
//        mqttMessage.setPayload("assembly success".getBytes());
//        mqttClient.subscribe(MQTT_TOPIC, MQTT_QOS);
//
//    }
//
//    public static void subscribe(MqttTopic topic, MqttMessage message) throws MqttException {
//        logger.info("topic is " + topic.toString() + " The message to be sent is " + message.toString());
//        //MqttDeliveryToken publish_token = topic.publish(message);
//        //publish_token.waitForCompletion();
//        logger.info("message has been pushed to the client");
//    }
//
//    public static void main(String[] args) throws Exception {
//        ClientRunnable client = new ClientRunnable();
//        //if (client != null) {
//            client.start();
//        //}
//    }
//}
//
//
//*/
