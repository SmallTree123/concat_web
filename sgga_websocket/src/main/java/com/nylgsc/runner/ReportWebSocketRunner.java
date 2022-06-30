package com.nylgsc.runner;

import cn.hutool.json.JSONUtil;
import com.nylgsc.server.EngineeringReportWebSocket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author rendj
 * @Description 报告websocket全体推送信息
 * @Date 9:43 2020/12/24
 **/
@Component
@Slf4j
public class ReportWebSocketRunner implements ApplicationRunner {

    public final static String REPORT_SCHEDULE_KEY_PREFIX = "engineering_report_schedule";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private AtomicInteger atomicInteger= new AtomicInteger(0);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(
            new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    while (true){
                        System.out.println("websocket监听中："+atomicInteger.getAndIncrement()+"s");
                        ConcurrentHashMap<String, EngineeringReportWebSocket> webSocketClients = EngineeringReportWebSocket.webSocketClients;
                        if(webSocketClients.size() > 0) {
                            try {
                                //redis取值
                                List<Object> redisData = new ArrayList<>();

                                 redisData.add(new Object());

//                                Set<String> keys = redisTemplate.keys(REPORT_SCHEDULE_KEY_PREFIX + ":*");
//                                if (keys != null && keys.size() > 0) {
//                                    keys.forEach(key -> {
//                                        String json = redisTemplate.boundValueOps(key).get();
//                                        EngineeringReportScheduleVo reportScheduleVo = JSONUtil.toBean(json, EngineeringReportScheduleVo.class);
//                                        log.info(reportScheduleVo.toString());
//                                        redisData.add(reportScheduleVo);
//                                    });
//                                }
                                String resposeJson = JSONUtil.toJsonStr(redisData);

                                //群发信息
                                for (String sessionId: webSocketClients.keySet()){
                                    try {
                                        webSocketClients.get(sessionId).getSession().getBasicRemote().sendText("hello-->"+sessionId+"    text:"+resposeJson);
                                    }catch (Exception e){
                                        log.error(e.getMessage(), e);
                                    }
                                }
                            }catch (Exception e){
                                log.error(e.getMessage(), e);
                            }
                        }
                        //3秒刷新一次
                        Thread.sleep(3000l);
                    }
                }
            }
        );
    }

}