package com.nylgsc.controller.config;

import com.nylgsc.controller.filter.SM2SignFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class SM2SignFilterConfig {

    @Bean
    public FilterRegistrationBean<SM2SignFilter> sm2SignFilterRegistrationBean() {
        FilterRegistrationBean<SM2SignFilter> sm2SignFilterRegistrationBean = new FilterRegistrationBean();
        sm2SignFilterRegistrationBean.setFilter(new SM2SignFilter());
        sm2SignFilterRegistrationBean.setOrder(10);
        sm2SignFilterRegistrationBean.addUrlPatterns(new String[]{"/*"});
        sm2SignFilterRegistrationBean.setName("sm2SignFilter");
        sm2SignFilterRegistrationBean.addInitParameter("excludes", "classpath:configs/permission.url.sign.properties");
        return sm2SignFilterRegistrationBean;
    }

}
