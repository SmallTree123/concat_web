package com.nylgsc.controller;

import com.nylgsc.service.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caffeine")
public class CaffeineController {

    @Autowired
    private CaffeineService caffeineService;

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key){
        return caffeineService.cacheIZUUL(key);
    }

    @GetMapping("/put/{key}")
    public String put(@PathVariable String key){
        return caffeineService.cachePutIZUUL(key);
    }
}
