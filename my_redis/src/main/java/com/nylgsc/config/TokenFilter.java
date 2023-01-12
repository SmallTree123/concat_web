package com.nylgsc.config;

import com.nylgsc.entity.LoginUser;
import com.nylgsc.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/user/sendVerificationCode",
                    "/user/login","/oauth/access_token","/chart/updateUserGroupDailyChartData","/chart/updateUserCompareChartData"
            )));

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            doFilter(request, response, filterChain);
        } catch (Exception e) {
            LOGGER.error(">>> 服务器运行异常，具体信息为：{}",  e);
            throw new RuntimeException(e);
        }
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if(!allowedPath){
            LoginUser user = tokenService.getLoginUser(request);
            if(user == null){
                throw new RuntimeException("页面过期，请关闭页面，请重新进入");
                return;
            }
            tokenService.verifyToken(user);
            LoginUserContextHolder.setLoginUser(user);
        }
        // 3.其他情况放开过滤
        filterChain.doFilter(request, response);
        LoginUserContextHolder.remove();
    }


}
