package com.nylgsc.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {


    private static String secretKey;

    private static String aa;//测试静态变量注入

    /**
     * 静态变量注入
     * 从配置文件读取jjwt.key属性
     * 注入key，set方法不能是static
     * @param secretKey
     */
    @Value("${jjwt.key}")
    public void setSecretKey(String secretKey) {
        JwtUtils.secretKey = secretKey;
    }

    private static String KEY_CLAIMS = "key_claims";
    private static String SUBJECT = "key_subject";

    private JwtUtils(){

    }


    /**
     * 生成token
     * @return
     * @throws Exception
     */
    public static String getToken() throws Exception {
        Date now = new Date();

        Map<String, Object> claims = new HashMap<String, Object>();
        LoginUser user = new LoginUser();
        user.setId(1000L);
        user.setUsername("123");
        claims.put(KEY_CLAIMS, user);
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = Jwts.builder().setClaims(claims).setSubject("Joe").signWith(key).compact();
        return token;
    }




    /**
     * 获取自定义密钥
     * @return
     */
    private static byte[] getSecretKey() {
        //log.info("secretKey = " + secretKey);
        if(StringUtils.isBlank(secretKey)) {
            throw new RuntimeException("jjwt配置的密钥不能为空");
        }
        return Decoders.BASE64.decode(secretKey);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getToken());
    }

}