package com.nylgsc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaffeineService {

    @Cacheable(value = "IZUUL",key = "#key")
    public String cacheIZUUL(String key){
        log.info("cacheIZUUL()方法执行------------");
        return getCache(key);
    }

    @CachePut(value = "IZUUL",key = "#key")
    public String cachePutIZUUL(String key){
        log.info("cacheIZUUL()方法执行------------");
        return "cachePutIZUUL-----------"+key;
    }

    private String getCache(String key) {
        try {
            log.info("getCache()方法执行---------");
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

}
