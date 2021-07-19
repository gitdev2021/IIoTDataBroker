package com.rozeta.IIoTDataBroker.TCP;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.rozeta.IIoTDataBroker.controller.NettySettings.PORT;

@Controller
public class NettyController {

    @PostConstruct
    private void start() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    new NettySocketServer(PORT).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    @PreDestroy
    private void destory() {

    }
}