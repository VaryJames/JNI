package com.liuhong.app;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import android.util.Base64;

import java.security.Key;

public class Des3Demo {
    public Des3Demo() throws Exception {
        //密钥
        byte[] key = Base64.decode("12345678901234567890123456789000", Base64.DEFAULT);
        byte[] keyiv = {1, 2, 3, 4, 5, 6, 7, 8};
        //原始数据
        String mainStr = "达达ABCacb123";
        //原始数据转byte
        byte[] data = mainStr.getBytes("UTF-8");
        System.out.println("原始数据:" + mainStr);
        System.out.println("ECB加密解密");
        //加密后的byte[]
        byte[] encodeByte_ECB = des3EncodeECB(key, data);
        //加密后的String   需把加密的byte[]转base64
        String encodeString_ECB = Base64.encodeToString(encodeByte_ECB, Base64.DEFAULT);
        System.out.println("加密的数据是:" + encodeString_ECB);
        //解密后的原始byte[] 需把加密后的byte[]转bass64
        byte[] decodeByteMain_ECB = Base64.decode(encodeString_ECB, Base64.DEFAULT);
        //解密后的byte[]
        byte[] decodeByte_ECB = ees3DecodeECB(key, decodeByteMain_ECB);
        //解密后的String
        String decodeString_ECB = new String(decodeByte_ECB, "UTF-8");
        System.out.println("解密后的数据是:" + decodeString_ECB);
        System.out.println("CBC加密解密");
        //加密后的byte[]
        byte[] encodeByte_CBC = des3EncodeCBC(key, keyiv, data);
        //加密后的String   需把加密的byte[]转base64
        String encodeString_CBC = Base64.encodeToString(encodeByte_CBC, Base64.DEFAULT);
        System.out.println("加密的数据是:" + encodeString_CBC);
        //解密后的原始byte[] 需把加密后的byte[]转bass64
        byte[] decodeByteMain_CBC = Base64.decode(encodeString_CBC, Base64.DEFAULT);
        //解密后的byte[]
        byte[] decodeByte_CBC = des3DecodeCBC(key, keyiv, decodeByteMain_CBC);
        //解密后的String
        String decodeString_CBC = new String(decodeByte_CBC, "UTF-8");
        System.out.println("解密后的数据是:" + decodeString_CBC);
    }

    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    public static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;

    }

    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }


    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(keyiv);

        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] bOut = cipher.doFinal(data);

        return bOut;

    }
}