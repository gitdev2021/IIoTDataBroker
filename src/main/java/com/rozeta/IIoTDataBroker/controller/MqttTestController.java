package com.rozeta.IIoTDataBroker.controller;

import com.rozeta.IIoTDataBroker.MQTT.MqttPushClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  UI MQTT 기능테스트를 만들고자 했으나 ... 파라미터 연동에서 막히고 있음 ㅠㅠ
 *
 * @author bam
 * 2020 March 5th 2013
 * TestController.java
 *
 */
@Controller
@RequestMapping("/MQTT")
public class MqttTestController {

    @Autowired
    private MqttPushClient mqttPushClient;

    // http://localhost:8080/MQTT/MqttService.html 호출
    @GetMapping("/MqttService")
    public String home() {
        return "MqttService";
    }

    @PostMapping(value="/MqttService")
    public String publishTopic(
            @RequestParam String topic, @RequestParam String jMessage) {

        String topicString = topic;

        //JSONObject jsonMessage = new JSONObject(jMessage);

        mqttPushClient.publish(0, false, topicString, jMessage);

        MqttMessage mqttMessage = new MqttMessage();
        MqttClient client;

        String message = null;
        message = new String(mqttMessage.getPayload());

        return "MqttService";
    }



//    // Send custom message content (using default theme)
////    @RequestMapping("/publishTopic/{data}")
////    public String test1(@PathVariable("data") String data) {
////        String topicString = "test";
////        mqttPushClient.publish(0,false,topicString, data);
////        return "ok";
////    }
//
////    // Send custom message content and specify subject
////    @RequestMapping("/publishTopic/{topic}/{data}")
////    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
////
////        // Machine 에서 OPC-DA를 통해 필요한 데이터를 가져온 후 [data]로 하여 MQTT 를 발행한다.
////        // mosquitto_sub 실행 후 MQTT 메세지 수신을 확인한다.
////
////        mqttPushClient.publish(0,false,topic, data);
////        return "ok";
////    }
//
//    /**
//     * 1. [MQTT_TOPIC]을 Path 파라미터로 설정하고 [MQTT_MESSAGE]를 JSON 메세지로 설정하여
//     *     POST 방식으로 MQTT 메세지를 발행한다. (IoTMessage, {"DATA":"Temp=35,Humi=78"})
//     * 2. [MQTT Subscribe] 모듈에서는 발행한 MQTT_TOPIC 을 구독하여 메세지를 전달 받는다.
//     * @param topic
//     * @param message
//     * @return
//     * @throws JSONException
//     */
//    @RequestMapping(value = "/publishTopic/{topic}", method = RequestMethod.POST)
//    public String subscribe(@PathVariable("topic") String topic, @RequestBody String message) throws JSONException {
//
//        JSONObject jsonObject = new JSONObject(message);
//        mqttPushClient.publish(0,false,topic, jsonObject);
//
//        return jsonObject.toString();
//    }
//
    /**
     * Convert MAP Object to JSON
     * @param  map
     * @return JSONObject
     * @throws JSONException
     */
    public JSONObject convertMapToJson(Map<String, Object> map) throws JSONException {

        JSONObject json = new JSONObject();
        String key = "";
        Object value = null;
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            json.put(key, value);
        }
        return json;
    }

}