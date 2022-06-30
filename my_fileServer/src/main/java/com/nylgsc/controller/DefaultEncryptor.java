package com.nylgsc.controller;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.sql.Connection;
import java.sql.DriverManager;


public class DefaultEncryptor{
    public static void main(String[] args) throws Exception {
        // 初始化加密算法
//        Cipher cipher = Cipher.getInstance("SM4/CBC/PKCS1Padding");
        SM4 s4 = new SM4(Mode.CBC, Padding.PKCS5Padding, "12HDESAAHIHQWERT".getBytes(), "1QAZ2WSX3EDC4RFV".getBytes());
        String decryptStr = s4.decryptStr("1969f66f67c4213e2942bcf817ec7524");
        System.out.println(decryptStr);

        
    }

}
