//
//package com.rozeta.IIoTDataBroker.MQTT;
//
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//
///**
// * Created by pengcheng.du on 2018/10/12.
// */
//
//public class MqttPushCallBackMultiModule implements MqttCallback {
//    @Override
//         // Handle the link disconnected
//    public void connectionLost(Throwable throwable) {
//        // After the connection is lost, it is usually reconnected here.
//
//        System.out.println("Connection disconnected, you can do reconnection");
//
//
//    }
//
//    @Override
//         // Processing the message delivery
//    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
//        System.out.println("Receive Message Subject: " + s);
//        System.out.println("Receive message Qos: " + mqttMessage.getQos());
//        System.out.println("Receive message content: " + new String(mqttMessage.getPayload()));
//
//    }
//
//    @Override
//    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//    }
//}
//
//
