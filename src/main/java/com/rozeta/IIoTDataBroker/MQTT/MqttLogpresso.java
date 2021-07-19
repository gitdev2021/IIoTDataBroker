package com.rozeta.IIoTDataBroker.MQTT;

import com.rozeta.IIoTDataBroker.controller.Settings;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.logpresso.client.Cursor;
import com.logpresso.client.Logpresso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MqttLogpresso {

    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    private String tableName = "";

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public MqttLogpresso() {
    }

    public void insert(String topic, MqttMessage mqttMessage) throws IOException {

        String message = null;
        message = new String(mqttMessage.getPayload());

        // MqttClientSetting 수신하기 위해서는 [MQTT_TOPIC] 토픽과 일치할 것!
        switch (topic) {
            case "IoTDevice":
                setTableName("mqtt_iotdevice");
                break;
            case "DataPARC":
                setTableName("mqtt_dataparc");
                break;
            default:
                setTableName("mqtt_input");
                break;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("json \"{}\"");
        sb.append(" | eval message = \"");
        sb.append(message);
        sb.append("\" | import ");
        sb.append(getTableName());

        logger.info( sb.toString() );

        Logpresso client = null;
        Cursor cursor = null;

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);

            client.query( sb.toString() );

        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (client != null)
                client.close();
        }
    }
}
