//
//package com.rozeta.IIoTDataBroker.MQTT;
//
//import org.eclipse.paho.client.mqttv3.*;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//
///**
// * Created by pengcheng.du on 2018/10/12.com.example.demo.mqtt.server.Server
// */
//
//
///* server side pushes different topics to the client */
//
//public class Server {
//    public static final String SERVER_URL = "tcp://127.0.0.1:1883";
//    public static List<String> TOPICList = new ArrayList<>(Arrays.asList("Weather","IoTDevice","OPC-DA"));
//    public static String clientId;
//
//    private MqttClient mqttClient;
//    public  MqttTopic mqttTopic;
//    private String username = "admin";
//    private String password = "password";
//
//    private MqttMessage mqttMessage;
//
///**
//    // Use this constructor when creating a new topic link
//    public Server() throws Exception {
//        mqttClient = new MqttClient(SERVER_URL, clientId, new MemoryPersistence());
//        Server server = new Server();
//        server.mqttMessage = new MqttMessage();
//        server.mqttMessage.setQos(1);
//        server.mqttMessage.setRetained(true);
//        server.mqttMessage.setPayload("assembly success".getBytes());
//        server.publish(server.mqttTopic, server.mqttMessage);
//        System.out.println(server.mqttMessage.isRetained() + ":retained state");
//
//        //Set Initial Topic to Weather
//        String topic = "Weather";
//        connect(topic);
//    }
//    */
//
//
//    public Server() throws Exception{
//        clientId = "Weather";
//        mqttClient = new MqttClient(SERVER_URL, clientId, new MemoryPersistence());
//        String topic = "Weather";
//        connect(topic);
//    }
//
//    public Server(String topic) throws Exception{
//        TOPICList.add(topic);
//        clientId = "Temperature";
//        mqttClient = new MqttClient(SERVER_URL, clientId, new MemoryPersistence());
//        this.createCon(topic);
//    }
//
//    public Server(String topic, String clientId) throws Exception{
//        TOPICList.add(topic);
//        mqttClient = new MqttClient(SERVER_URL, clientId, new MemoryPersistence());
//        this.createCon(topic);
//        //connect(topic);
//
//    }
//
//    public void connect(String topic){
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.setCleanSession(false);
//        mqttConnectOptions.setUserName(username);
//        mqttConnectOptions.setPassword(password.toCharArray());
//
//        mqttConnectOptions.setConnectionTimeout(20);
//        mqttConnectOptions.setKeepAliveInterval(20);
//
//        try {
//            mqttClient.setCallback(new MqttPushCallBack());
//            mqttClient.connect(mqttConnectOptions);
//            mqttTopic = mqttClient.getTopic(topic);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void publish(MqttTopic topic, MqttMessage message) throws MqttException {
//        System.out.println("topic is "+topic.toString()+" The message to be sent is "+message.toString());
//        MqttDeliveryToken publish_token = topic.publish(message);
//        publish_token.waitForCompletion();
//        System.out.print ("message has been pushed to the client");
//    }
//    public static void main(String[] args) throws Exception {
//        Server server = new Server();
//        server.mqttMessage = new MqttMessage();
//        server.mqttMessage.setQos(1);
//        server.mqttMessage.setRetained(false);
//        server.mqttMessage.setPayload("assembly success".getBytes());
//        server.publish(server.mqttTopic,server.mqttMessage);
//        System.out.println(server.mqttMessage.isRetained()+":retained state");
//    }
//    public String createCon(String topic) throws Exception{
//
//        Server server = new Server();
//        server.mqttMessage = new MqttMessage();
//        server.mqttMessage.setQos(1);
//        server.mqttMessage.setRetained(false);
//        server.mqttMessage.setPayload("assembly success".getBytes());
//        server.publish(server.getMqttClient().getTopic(topic),server.mqttMessage);
//        System.out.println(server.mqttMessage.isRetained()+":retained state");
//        return "build success";
//    }
//
//    public static String getServerUrl() {
//        return SERVER_URL;
//    }
//
//    public List<String> getTOPIC() {
//        return TOPICList;
//    }
//
//    public static String getClientId() {
//        return clientId;
//    }
//
//    public MqttClient getMqttClient() {
//        return mqttClient;
//    }
//
//    public MqttTopic getMqttTopic() {
//        return mqttTopic;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public MqttMessage getMqttMessage() {
//        return mqttMessage;
//    }
//}
