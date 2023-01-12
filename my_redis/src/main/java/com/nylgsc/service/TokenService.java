package com.nylgsc.service;

import com.nylgsc.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    LoginUser getLoginUser(HttpServletRequest request);
}
