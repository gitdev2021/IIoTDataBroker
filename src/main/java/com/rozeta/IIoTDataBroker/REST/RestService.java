package com.rozeta.IIoTDataBroker.REST;

import com.logpresso.client.Cursor;
import com.logpresso.client.Logpresso;
import com.rozeta.IIoTDataBroker.controller.Settings;
import com.rozeta.IIoTDataBroker.domain.IoTDevice;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class RestService {

    /**
     * IoT Device Interface with GET Method
     */
    @GetMapping("/REST/test")
    public String IoTDeviceInsert() throws IOException {

        Logpresso client = null;
        Cursor cursor = null;

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query("proc sp_dsSub22()");
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
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

        IoTDevice iotDevice = new IoTDevice();

        return "Test Successfully Done!";
    }

    /**
     * IoT Device Interface with GET Method
     */
    @GetMapping("/REST/GET/input")
    public IoTDevice interfaceGetArgs(
            @RequestParam(value = "flag")String flag,
            @RequestParam(value = "message")String message) {

        // Get Method 로 전달 받은 값을 객체에 담고, 전달받은 객체를 리턴하여 동작하는지 확인.
        IoTDevice iotDevice = new IoTDevice();
        iotDevice.setFlag(flag);
        iotDevice.setMessage(message);

        return iotDevice;

    }


    /**
     * IoT Device Interface with POST Method
     * @param param
     * @return <NONE> iotDevice
     */
    @PostMapping("/REST/POST/input")
    public IoTDevice interfacePostArgs(
            @RequestBody IoTDevice param ) throws IOException {

        // POST Method 로 전달 받은 값을 객체에 담고, 전달받은 객체를 리턴하여 동작하는지 확인.
        String flag = param.getFlag();
        String msg = param.getMessage();

        /**
         *   IoT Device 에서 받아온 값을 처리한다. 예를 들면,
         *   - DB 입력
         *   - 로그프레소 입력
         *   - Machine Learning 전달
         */

        Logpresso client = null;
        Cursor cursor = null;
        String queryStr = null;
        queryStr = String.format("proc sp_getIot( \"%s\", \"%s\" )", flag, msg);

        IoTDevice iotDevice = new IoTDevice(flag, queryStr);


        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query(queryStr);
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

        return iotDevice;

    }


    @GetMapping("/REST/output")
    public List<IoTDevice> DeviceResponse() throws IOException {

        Logpresso client = null;
        Cursor cursor = null;


        IoTDevice iotDevice = new IoTDevice();
        List<IoTDevice> deviceList = new ArrayList();

//      String flag = "STATUS";
        String flag = "MEASURE";

        try {
            client = new Logpresso();
            client.connect(Settings.HOST, Settings.USER, Settings.PASSWORD);
            cursor = client.query("proc sp_dsSub22()");
            while (cursor.hasNext()) {
                iotDevice.setFlag(flag);
                iotDevice.setMessage(cursor.next().toString());
                deviceList.add(iotDevice);
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

        return deviceList;

    }

}
