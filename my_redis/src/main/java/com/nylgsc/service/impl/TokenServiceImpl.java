package com.nylgsc.service.impl;

import com.nylgsc.entity.LoginUser;
import com.nylgsc.service.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(LoginConstant.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            LoginUser user = redisCache.getCacheObject(userKey);
            return user;
        }
        return null;
    }
}
