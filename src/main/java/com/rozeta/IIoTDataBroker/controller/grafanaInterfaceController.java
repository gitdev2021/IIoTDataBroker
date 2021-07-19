package com.rozeta.IIoTDataBroker.controller;

import com.logpresso.client.Cursor;
import com.logpresso.client.Logpresso;
import com.rozeta.IIoTDataBroker.domain.IoTDevice;
import org.apache.tomcat.util.buf.StringUtils;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@RestController
public class grafanaInterfaceController {

    @GetMapping("/grafana/test")
    public String grafanaSend() throws IOException, JSONException, ParseException {

        Logpresso client = null;
        Cursor cursor = null;

         ArrayList<String> mMsg = new ArrayList<>();

        StringBuffer sb = new StringBuffer();

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query("proc sp_temp()");
            while (cursor.hasNext()) {
                // System.out.println(cursor.next());
                mMsg.add(cursor.next().toString().replace('=', ':'));
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




        //jsonMessage = convertMapToJson(mMsg);
        sb.append("[");
        sb.append(StringUtils.join(mMsg, ','));
        sb.append("]");

        System.out.println( sb.toString() );
        // JSONParser jsonParse = new JSONParser();
        // JSONObject jsonMessage =  (JSONObject)jsonParse.parse(sb.toString());

        return sb.toString();
    }

    public JSONObject convertMapToJson(Map<String, Object> map) throws JSONException {

        JSONObject json = new JSONObject();
        String key = "";
        Object value = null;
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            System.out.println("value = " + value);

            json.put(key, value);

        }
        return json;
    }


    @GetMapping("/grafana/test2")
    public String grafanaJson() throws IOException, JSONException, ParseException {

        Logpresso client = null;
        Cursor cursor = null;

        StringBuffer sb = new StringBuffer();

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query("proc sp_tempj()");

            while (cursor.hasNext()) {
                sb.append(cursor.next());
                if (cursor.hasNext()) {
                    sb.append(",");
                }
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

        //jsonMessage = convertMapToJson(mMsg);
        StringBuffer sbout = new StringBuffer();
        sbout.append(sb.toString().replace('=', ':'));

        // JSONParser jsonParse = new JSONParser();
        // JSONObject jsonMessage =  (JSONObject)jsonParse.parse(sb.toString());

        return sbout.toString();
    }

}
