package com.nylgsc.controller;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;

    /**
     * H5登录页面账号
     */
    private String username;

    /**
     * H5登录页面密码
     */
    private String password;

}
