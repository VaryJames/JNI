package com.liuhong.app;

import android.text.TextUtils;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2015/4/14.
 */
public class NativeUtil {

    static {
        System.loadLibrary("ppsec_c2");
        System.loadLibrary("ppsec_c");
        System.loadLibrary("ppsec");

    }

    public void test(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        System.out.println("msg = " + msg);
        byte[] bytes = msg.getBytes();
        System.out.println("bytes.length = " + bytes.length);
        System.out.println("bytes bytesToHex = " + bytesToHex(bytes));

        NativeData data = new NativeData();
        encryptMsg(bytes, bytes.length, data);

        byte[] encBytes = data.getByteData();
        System.out.println("encBytes.length = " + encBytes.length);
        System.out.println("encBytes bytesToHex = " + bytesToHex(encBytes));
        String encStr = new String(encBytes);
        System.out.println("encStr = " + encStr);

        NativeData data1 = new NativeData();
        decryptMsg(encBytes,encBytes.length,data1);

        byte[] decBytes = data1.getByteData();
        System.out.println("decBytes.length = " + decBytes.length);
        System.out.println("decBytes bytesToHex = " + bytesToHex(decBytes));
        String decStr = new String(decBytes);
        System.out.println("decStr = " + decStr);

    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        return new String(bytesToChars(bytes));
    }

    public static char[] bytesToChars(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return hexChars;
    }

    public native int encryptMsg(byte[] inData, int inLen, NativeData outData);

    public native int decryptMsg(byte[] inData, int inLen, NativeData outData);

    public native int doKeyExchangeA(NativeData data, String rootPath, int appId);

    public native int doKeyExchangeB(byte[] data);

    public native String getServerUrl();

    public native int encryptSensitiveData(int datatype, byte[] data, int datalen, NativeData outdata);
}
