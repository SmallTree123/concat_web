package com.nylgsc.MyXxxJob.controller;

import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyJobTask {
    private Logger logger = LoggerFactory.getLogger(MyJobTask.class);

    // 简单任务
    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() throws Exception {
        logger.info("我是被 xxl-job 自动调用执行的任务！" + DateUtil.formatDateTime(new Date()));

    }
}
