package com.nylgsc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author aibao
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 加密秘钥
     */
    private String key;
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
//    private UserInfoVo user;

}
