package com.nylgsc.controller.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.PlainDSAEncoding;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SM2Util {

    static final Charset CHAR_SET = Charset.forName("utf-8");

    /**
     * 生成sm2密钥对
     * @return
     */
    public Map<String, String> generateKeyPair(){
        SM2 sm2 = SmUtil.sm2();
        byte[] privateKeyByte = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        byte[] publicKeyByte = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);

        String publicKey = HexUtil.encodeHexStr(publicKeyByte);
        String privateKey = HexUtil.encodeHexStr(privateKeyByte);
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", publicKey);
        map.put("privateKey", privateKey);
        log.info("publicKey: {}", publicKey);
        log.info("privateKey: {}", privateKey);
        return map;
    }

    /**
     * 公钥加密
     * @param data 明文
     * @param publicKeyHex 公钥
     * @return
     */
    public String encrypt(String data, String publicKeyHex){
        if (publicKeyHex.length() == 130) {
            publicKeyHex = publicKeyHex.substring(2);
        }
        String xhex = publicKeyHex.substring(0, 64);
        String yhex = publicKeyHex.substring(64, 128);
        ECPublicKeyParameters ecPublicKeyParameters = BCUtil.toSm2Params(xhex, yhex);

        SM2 sm2 = getSm2Instance();
        sm2.setPublicKeyParams(ecPublicKeyParameters);
        String encryptHex = sm2.encryptHex(data, KeyType.PublicKey);
        return encryptHex;
    }

    /**
     * 私钥解密
     * @param data 密文
     * @param privateKeyHex 私钥
     * @return
     */
    public String decrypt(String data, String privateKeyHex){
        ECPrivateKeyParameters privateKeyParameters = BCUtil.toSm2Params(privateKeyHex);
        SM2 sm2 = getSm2Instance();
        sm2.setPrivateKeyParams(privateKeyParameters);
        String decryptStr = sm2.decryptStr(data, KeyType.PrivateKey, CHAR_SET);
        return decryptStr;
    }

    /**
     * 私钥签名(签名规则：纯签名 + 生成椭圆曲线点 + sm3杂凑)
     * @param data 原始签名字符串
     * @param privateKeyHex 私钥
     * @return
     */
    public String sign(String data, String privateKeyHex){
        ECPrivateKeyParameters privateKeyParameters = BCUtil.toSm2Params(privateKeyHex);
        SM2 sm2 = getSm2Instance();
        sm2.setPrivateKeyParams(privateKeyParameters);
        String signStr = sm2.signHex(HexUtil.encodeHexStr(data));
        return signStr;
    }

    /**
     * 公钥验签
     * @param data 原始签名字符串
     * @param signHex 签名
     * @param publicKeyHex 公钥
     * @return
     */
    public boolean verify(String data, String signHex, String publicKeyHex){
        if (publicKeyHex.length() == 130) {
            publicKeyHex = publicKeyHex.substring(2);
        }
        String xhex = publicKeyHex.substring(0, 64);
        String yhex = publicKeyHex.substring(64, 128);
        ECPublicKeyParameters ecPublicKeyParameters = BCUtil.toSm2Params(xhex, yhex);

        SM2 sm2 = getSm2Instance();
        sm2.setPublicKeyParams(ecPublicKeyParameters);
        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(data), signHex);
        return verify;
    }

    /**
     * 获取sm2实例
     * @return
     */
    private SM2 getSm2Instance(){
        SM2 sm2= new SM2();
        sm2.setMode(SM2Engine.Mode.C1C2C3);
        sm2.setEncoding(PlainDSAEncoding.INSTANCE);
        sm2.setDigest(new SM3Digest());
        return sm2;
    }

    /**
     * 生成密钥对进行加解密以及签名与验签
     */
    /*private void example1(){
        SM2Util sm2Util = new SM2Util();
        //生成密钥对
        Map<String, String> keyMap = sm2Util.generateKeyPair();
        String publicKeyStr = keyMap.get("publicKey");
        String privateKeyStr = keyMap.get("privateKey");
        System.out.println("公钥："+publicKeyStr);
        System.out.println("私钥："+privateKeyStr);

        String encryptData = "我是一行加密数据！";
        System.out.println("预加密字符串："+encryptData);
        // 公钥加密
        String encrypt = sm2Util.encrypt(encryptData, publicKeyStr);
        System.out.println("加密："+encrypt);
        // 私钥解密
        String decrypt = sm2Util.decrypt(encrypt, privateKeyStr);
        System.out.println("解密："+decrypt);

        //私钥签名
        String signData = "abc";
        System.out.println("预签名字符串："+signData);
        String sign = sm2Util.sign(signData, privateKeyStr);
        System.out.println("签名："+sign);
        //公钥验签
        boolean verify = sm2Util.verify(signData, sign, publicKeyStr);
        System.out.println("验签："+verify);
    }*/

    /**
     * 公钥验签例子
     */
    /*private void example2(){
        String data = "原始数据";
        String sign = "deb2fa807a3f340525179fb9b1aec5ba7d5cb6e420a6db3738eaf8bf862a390aa871c98a31208cd31ac4fe95c807a1996d121b132b3608d032f2a7a4efefe244";
        String publicKeyHex = "0420fd62af18d24291b48b32163f17cee239d553f7a8574b48aa99b8d18300086558d433a325a12c4854a82bfc1111ff686e2c96bf48580c7f1d0d10eb3279a6fe";

        SM2Util sm2Util = new SM2Util();
        boolean verify = sm2Util.verify(data, sign, publicKeyHex);
        System.out.println(verify);
    }*/


    /*public static void main(String[] args) {

        //new SM2Util().example1();
        new SM2Util().example2();

    }*/

}
