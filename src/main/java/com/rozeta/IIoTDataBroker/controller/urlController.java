package com.rozeta.IIoTDataBroker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class urlController {
    @GetMapping("/")
    public String homeNavigator() {
        return "home";
    }

}
