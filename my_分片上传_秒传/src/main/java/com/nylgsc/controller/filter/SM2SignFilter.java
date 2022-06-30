package com.nylgsc.controller.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.setting.dialect.Props;
import com.nylgsc.controller.constant.Constant;
import com.nylgsc.controller.utils.HandleUtil;
import com.nylgsc.controller.utils.SM2Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Slf4j
public class SM2SignFilter implements Filter {

    public List<Object> excludes = new ArrayList();

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("======SM2 SIGN FILTER INIT======");
        String excludesPath = filterConfig.getInitParameter("excludes");
        Props props = new Props(excludesPath);
        Set<Object> set = props.keySet();
        this.excludes = new ArrayList(set);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入doFilter...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //白名单链接进行签名校验
        if (HandleUtil.excludeURL(this.excludes, httpServletRequest, httpServletResponse)) {
            try {
                log.info("白名单链接进行签名校验");

                String appid = getValue(httpServletRequest, Constant.APPID);
                String timestamp = getValue(httpServletRequest, Constant.TIMESTAMP);
                String sign = getValue(httpServletRequest, Constant.SIGN);
                String publicKey = getPublicKey(appid);

                if (StringUtils.isBlank(sign)) {
                    errorResponse(httpServletResponse, "签名不为空，请检查!");
                } else if (StringUtils.isBlank(timestamp)){
                    errorResponse(httpServletResponse, "签名时间戳不为空，请检查!");
                } else if (!belongCalendar(timestamp)){
                    errorResponse(httpServletResponse, "签名已过期，请检查!");
                } else if(StringUtils.isBlank(publicKey) ){
                    errorResponse(httpServletResponse, appid + "没授权，请检查!");
                }
                else {
                    SM2Util sm2Util = new SM2Util();
                    //应用id(pms_appid)、时间戳(pms_timestamp)，将两个参数字符串拼接成一个字符串进行验签
                    String data = appid + timestamp;
                    boolean verify = sm2Util.verify(data, sign, publicKey);
                    if (verify) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        errorResponse(httpServletResponse, "签名错误，请检查!");
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                errorResponse(httpServletResponse, e.getMessage());
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("调用destroy方法...");
    }



    /**
     * 获取值
     * @param request
     * @return
     */
    private String getValue(HttpServletRequest request, String paramKey) {
        String value = null;
        String headerValue = request.getHeader(paramKey);
        String parameterValue = request.getParameter(paramKey);
        String cookieValue = getCookieValue(request, paramKey);
        if (StringUtils.isNotBlank(headerValue)) {
            value = headerValue;
        } else if (StringUtils.isNotBlank(parameterValue)) {
            value = parameterValue;
        } else if (StringUtils.isNotBlank(cookieValue)) {
            value = cookieValue;
        }
        return value;
    }


    private String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                String cookieValue = cookie.getValue();
                return cookieValue;
            }
        }
        return null;
    }

    /**
     * 获取redis里面存储的公钥
     * @param appid
     * @return
     */
    private String getPublicKey(String appid) {
//        Object publicKey = redisTemplate.opsForHash().get("engineering:sm2:" + appid, "publicKey");
//        return publicKey==null?"":publicKey.toString();
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0rZ0x8dl2Vjdm4AT6z5QGYMoE\n" +
                "MfxZjFdpP4+aJkDVIwgtG0K3oczb/mpIIkIqOE7H2aV9umX2SokekdtR3jyZa/38\n" +
                "wXrOdk2Pgckgyv0B3n7PpYEOERm7MjEV47Fd7bOq2d1oZ3hT22Rg3F/xtBI1G5eR\n" +
                "4dRgxpSx71vv3l87xQIDAQAB";
    }

    /**
     * 响应错误
     * @param response
     * @throws IOException
     */
    private void errorResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(403);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("signerror", "SIGNERROR");
        PrintWriter out = response.getWriter();
        out.write(msg);
        out.flush();
        out.close();
    }


    /**
     * 判断时间戳是否在时间范围内
     * @param timestamp
     * @return
     */
    private boolean belongCalendar(String timestamp) {
        //当前时间
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(Convert.toLong(timestamp));
        //前两分钟
        Calendar beforeTime  = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -2);
        //Date beforeDate = beforeTime.getTime();
        //后两分钟
        Calendar afterTime  = Calendar.getInstance();
        afterTime.add(Calendar.MINUTE, 2);
        //Date afterDate = afterTime.getTime();

        boolean after = now.after(beforeTime);
        boolean before = now.before(afterTime);

        return (after && before);
    }
}
