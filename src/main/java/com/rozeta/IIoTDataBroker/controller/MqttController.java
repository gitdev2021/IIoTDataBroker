package com.rozeta.IIoTDataBroker.controller;

import com.logpresso.client.Cursor;
import com.logpresso.client.Logpresso;
import com.rozeta.IIoTDataBroker.MQTT.MqttPushClient;
import com.rozeta.IIoTDataBroker.domain.IoTDevice;
import lombok.extern.flogger.Flogger;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.config.xml.StandardHeaderEnricherParser;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bam
 * 2020 March 5th 2013
 * TestController.java
 *
 */
@RestController
@RequestMapping("MQTT")
public class MqttController {

    @Autowired
    private MqttPushClient mqttPushClient;

    @GetMapping(value = "/publishTopic")
    public String publishTopic() throws JSONException {
        String topicString = "IoTDevice";

        JSONObject jsonMessage = new JSONObject();
        Map mMsg = new HashMap<String, Object>();

        mMsg.put("Temp",    33.8);
        mMsg.put("Humi",    78);
        mMsg.put("Thermal", 34.2);
        mMsg.put("COGas",   22);
        mMsg.put("Flare",  "NONE");

        //jsonMessage = convertMapToJson(mMsg);
        String str = "{\"temp\":33.8,\"Humi\":78,\"Thermal\":34.2,\"COgas\":22,\"Smoke\":\"NONE\"}";

        mqttPushClient.publish(0, false, topicString, str);
        return "ok";
    }
    // Send custom message content (using default theme)
//    @RequestMapping("/publishTopic/{data}")
//    public String test1(@PathVariable("data") String data) {
//        String topicString = "test";
//        mqttPushClient.publish(0,false,topicString, data);
//        return "ok";
//    }

//    // Send custom message content and specify subject
//    @RequestMapping("/publishTopic/{topic}/{data}")
//    public String test2(@PathVariable("topic") String topic, @PathVariable("data") String data) {
//
//        // Machine ?????? OPC-DA??? ?????? ????????? ???????????? ????????? ??? [data]??? ?????? MQTT ??? ????????????.
//        // mosquitto_sub ?????? ??? MQTT ????????? ????????? ????????????.
//
//        mqttPushClient.publish(0,false,topic, data);
//        return "ok";
//    }

    /**
     * 1. [MQTT_TOPIC]??? Path ??????????????? ???????????? [MQTT_MESSAGE]??? JSON ???????????? ????????????
     *     POST ???????????? MQTT ???????????? ????????????. (IoTMessage, {"DATA":"Temp=35,Humi=78"})
     * 2. [MQTT Subscribe] ??????????????? ????????? MQTT_TOPIC ??? ???????????? ???????????? ?????? ?????????.
     * @param topic
     * @param message
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/publishTopic/{topic}", method = RequestMethod.POST)
    public String subscribe(@PathVariable("topic") String topic, @RequestBody String message) throws JSONException, UnsupportedEncodingException {

        if ( message == null || message.isEmpty() ) {
            System.out.println("Null Message");
            return "Error! No Args";
        } else {
            System.out.println(URLDecoder.decode(message, "utf-8") );
        };


        //JSONObject jsonObject = new JSONObject(message);

        mqttPushClient.publish(0,false,topic, message);

        return message;
    }

    @RequestMapping("/autopublish/{topic}")
    public String autoPublish(@PathVariable("topic") String topic) throws IOException {

        //JSONObject jsonObject = new JSONObject(message);
        String message = null;
        Logpresso client = null;
        Cursor cursor = null;
        String queryStr = null;

        queryStr = String.format("table limit=1 mqtt_dataparc");

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query(queryStr);
            while (cursor.hasNext()) {
                message = cursor.next().toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (cursor != null)
                cursor.close();
            if (client != null)
                client.close();
        }

        System.out.println("message = " + message);
        String msg = message.replace("\"", "\\\"");
        System.out.println("message replaced = " + msg);

        mqttPushClient.publish(0,false,topic, msg);

        return message;
    }

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

    // MAP => String ?????? ??????. (????????? ?????? ????????????) ^^ 2021.07
    public String convertMapToString(Map<String, Object> map) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        String key = null;
        Object value = null;
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            sb.append(key + ":");
            value = entry.getValue();
            sb.append(value + ",");
        }
        sb.append("}");

        return sb.toString();
    }
}