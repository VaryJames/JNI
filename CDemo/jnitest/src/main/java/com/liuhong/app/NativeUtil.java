package com.liuhong.app;

import android.text.TextUtils;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2015/4/14.
 */
public class NativeUtil {

    static {
        System.loadLibrary("ppsec");
    }

    public String getMd5(String msg) {

        if (TextUtils.isEmpty(msg)) {
            return "";
        }

        System.out.println("msg = " + msg);

        return getMd5(msg.length(), msg);

    }

    public native String getServerUrl();

    public native String getMd5(int len, String str);

    public native byte[] encrypt(byte[] inBytes,String key);
}
