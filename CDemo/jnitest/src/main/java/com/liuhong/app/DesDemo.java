package com.liuhong.app;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by Administrator on 2015/4/16.
 */
public class DesDemo {

    /**
     * 加密
     *
     * @param datasource
     * @param password
     * @return
     */
    public byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src
     * @param password
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    public void test() {

        String str = "12345678";

        //密码，长度要是8的倍数
        String password = "12345678";


        if (true) {
            return;
        }

        //待加密内容

        int a = str.length();

        int b = password.length();
        byte[] bb = password.getBytes();

        System.out.println("原始数据16进制；" + BinaryUtil.BinaryToHexString(str.getBytes()));
        byte[] c = str.getBytes();
        byte[] result = encrypt(str.getBytes(), password);
        System.out.println("加密后内容为：" + new String(result));
        System.out.println("加密数据16进制：" + BinaryUtil.BinaryToHexString(result));

        //直接将如上内容解密
        try {
            byte[] decryResult = decrypt(result, password);
            System.out.println("解密数据16进制：" + BinaryUtil.BinaryToHexString(decryResult));
            System.out.println("解密后内容为：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * C层加密
     *
     * @param str
     * @throws Exception
     */
    public void test2(String str) throws Exception {

        String key = "10081509";
        System.out.println("test2() 原数据：" + str);
        byte[] originData = str.getBytes("utf-8");

        byte[] data = originData;

        if (originData.length % 8 != 0) {
            data = getData(originData);
        }

        int n = data.length / 8;
        byte[] encryResult = new byte[2 * data.length];

        byte[] keyBytes = key.getBytes("utf-8");

        for (int i = 0; i < n; i++) {
            byte[] encryptData = getEncryData(data, i);
            String strTmp1 = new String(encryptData,"utf-8");
            NativeData encrytNativeData = new NativeData();
            NativeUtil.encrypt(encryptData, keyBytes, encrytNativeData);
            byte[] encrytData1 = encrytNativeData.getByteData();
            copyTo(encryResult, encrytData1, i, 16);
        }


        System.out.println("test2() 加密数据16进制：" + BinaryUtil.BinaryToHexString(encryResult));

        byte[] keyNewBytes = "10081509".getBytes("utf-8");

        byte[] decryptResult = new byte[data.length];
        for (int i = 0; i < n; i++) {
            NativeData encrytNativeData2 = new NativeData();
            byte[] encrytData = getDecrytData(encryResult, i);
            NativeUtil.decrypt(encrytData, keyNewBytes, encrytNativeData2);
            byte[] decryptData = encrytNativeData2.getByteData();
            String stringTmp = new String(decryptData,"utf-8");
            copyTo(decryptResult, decryptData, i, 8);
        }


        System.out.println("test2() 解密数据16进制：" + BinaryUtil.BinaryToHexString(decryptResult));

        String result = new String(decryptResult, "utf-8");
        System.out.println("test2() 解密string 数据：" + result);

        int index = result.indexOf('#');
        if (index != -1) {

            String finalResult = result.substring(0, index);
            System.out.println("test2() 解密string final 数据：" + finalResult);
        }

    }

    private byte[] getEncryData(byte[] data, int i) {
        byte[] encryData = new byte[8];
        for (int j = 0; j < 8; j++) {
            encryData[j] = data[i * 8 + j];
        }

        return encryData;
    }

    private byte[] getDecrytData(byte[] encryResult, int i) {
        byte[] returnbyte = new byte[16];
        for (int j = 0; j < 16; j++) {
            returnbyte[j] = encryResult[i * 16 + j];
        }
        return returnbyte;
    }

    private void copyTo(byte[] encryResult, byte[] encrytData, int i, int n) {
        for (int j = 0; j < encrytData.length; j++) {
            encryResult[i * n + j] = encrytData[j];
        }
    }

    private byte[] getData(byte[] originData) {


        int len = originData.length;

        int trueLen = len;

        switch (len % 8) {
            case 1:
                trueLen += 7;
                break;
            case 2:
                trueLen += 6;
                break;
            case 3:
                trueLen += 5;
                break;
            case 4:
                trueLen += 4;
                break;
            case 5:
                trueLen += 3;
                break;
            case 6:
                trueLen += 2;
                break;
            case 7:
                trueLen += 1;
                break;
        }

        byte[] trueBytes = new byte[trueLen];
        for (int i = 0; i < len; i++) {
            trueBytes[i] = originData[i];
        }

        for (int j = 0; j < trueLen - len; j++) {
            trueBytes[len + j] = (byte) 35; // 27 ESC
        }

        return trueBytes;
    }

}
