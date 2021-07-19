///*
//package com.rozeta.IIoTDataBroker.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.rozeta.IIoTDataBroker.MQTT.Server;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.MqttTopic;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//*/
///**
// *  [MQTT] Server / Client / SpringBootApplication 실행 후 사용함.
// *  IIoT Broker HTTP Controller
// *
// *  제거대상: alibaba/fastjson ( JSON message 파싱 목적으로 사용함)
// *
// *  [사용방법] 웹브라우저에서 URL 호출하기
// *  1. http://localhost:8080/mqtt/publishMessageOnTopic/weather (POST 메서드로 메시지 발행하기)
// *     JSON Message: { "message": "MQTT Test" }
// *  2. http://localhost:8080/mqtt/createNewTopic/{topic}/{clientId} (신규 주제 TOPIC 발행하기)
// *
// */
//
//
//@RequestMapping("MQTT")
//public class PublishMessage {
//    private static String HOST = "tcp://127.0.0.1:1883";
//    public static MqttClient client;
//
//    public static final String SERVER_URL = "tcp://127.0.0.1:1883";
//    //forward news
//    @RequestMapping(value = "/publishMessageOnTopic/{topic}", method = RequestMethod.POST)
//    public Map<String,Object> publishMessageOnTopic(
//            @PathVariable(value = "topic") String topic, @RequestBody String message) throws Exception {
//
//        Map<String,Object> map = new HashMap<>();
//
//        JSONObject jsonObject = (JSONObject) JSONObject.parse(message);
//
//        // JSON {"message":"contents"} 형식으로 인터페이스 데이터를 전송하여야 함!!!
//        String mess = (String) jsonObject.get("message");
//
//        MqttMessage mqttMessage = new MqttMessage();
//        mqttMessage.setPayload(mess.getBytes());
//        mqttMessage.setQos(1);
//        mqttMessage.setRetained(false);
//        if (this.findIndex(topic) == -1) {
//            map.put("data","no topic");
//            return map;
//        }
//        int index = this.findIndex(topic);
//
//        Server server = new Server(topic);
//        server.connect(topic);
//        //server.connect(topic);
//
//        MqttTopic mqttTopic = server.mqttTopic;
//        Server.publish(mqttTopic,mqttMessage);
//        map.put("data","Sent");
//        return map;
//    }
//    public int findIndex(String topic) throws Exception{
//        Server server = new Server();
//        final List<String> topicList = server.getTOPIC();
//        int index = 0;
//        for (String topic1: topicList) {
//            if (topic.equals(topic1)) {
//                break;
//            }
//            index++;
//        }
//        if (index==topicList.size()) {
//            return -1;
//        } else {
//            return index;
//        }
//    }
//    private  MqttClient getMqttClient(String clientId) throws MqttException {
//        if (client != null && clientId.equals(client.getClientId())) {
//            return client;
//        }
//        return new MqttClient(HOST, clientId);
//    }
//    //Create a theme
//    @RequestMapping(value = "/createNewTopic/{topic}/{clientId}",method = RequestMethod.POST)
//    public Map<String,Object> createNewTopic(@PathVariable(value = "topic") String topic ,
//                                             @PathVariable(value = "clientId") String clientId){
//        System.out.println("topic:"+topic+",clientId"+clientId);
//        try{
//            Server server = new Server(topic,clientId);
//            //server.connect(topic);
//            MqttTopic mTopic = server.getMqttClient().getTopic("");
//            System.out.println("topic1:"+ mTopic);
//        } catch (Exception e){
//            System.out.println(e.toString());
//        }
//
//        return null;
//    }
//}
//
