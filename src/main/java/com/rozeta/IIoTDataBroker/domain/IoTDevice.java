package com.rozeta.IIoTDataBroker.domain;

import java.io.Serializable;

public class IoTDevice implements Serializable {

    private String flag;
    private String message;

    public IoTDevice() {
    }
    public IoTDevice(String flag, String msg) {
        this.flag = flag;
        this.message = msg;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
