package com.rozeta.IIoTDataBroker.controller;

import com.rozeta.IIoTDataBroker.TCP.NettySocketClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import static com.rozeta.IIoTDataBroker.controller.NettySettings.*;

@RestController("/TCP")
public class TCPClientController {

    @GetMapping("/send")
    public String TCPTest() throws IOException {
        String tMessage = "TCP Client test for Spring!";

        try {
            new NettySocketClient(HOST, PORT, tMessage).run();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("Job Done!");
        }

        return "home";
    }
}