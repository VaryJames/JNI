package com.liuhong.app;

/**
 * Created by Administrator on 2015/4/14.
 */
public class NativeUtil {

    static {
        System.loadLibrary("ppsec");
    }

    public static String getMd5(String msg) {

        if (msg == null || msg == "") {
            return "";
        }

        System.out.println("msg = " + msg);

        return getMd5(msg.length(), msg);

    }

    public static native String getServerUrl();

    public static native String getMd5(int len, String str);

    public static native int encrypt(byte[] inBytes, byte[] key,NativeData nativeData);

    public static native int decrypt(byte[] inBytes, byte[] key,NativeData nativeData);
}
