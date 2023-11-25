package com.nylgsc.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/testGateWay")
    public String testGateWay() {
        return "testGateWay";
    }
}
