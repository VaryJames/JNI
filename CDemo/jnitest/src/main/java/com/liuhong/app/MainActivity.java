package com.liuhong.app;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView)findViewById(R.id.text);
        TextView textEnc = (TextView)findViewById(R.id.text_enc);
        TextView textDen = (TextView)findViewById(R.id.text_denc);
        NativeUtil util = new NativeUtil();
        text.setText("原值：1234567890!@#$%^&*()");
//
//        textEnc.setText("md5:"+util.getMd5("1234567890!@#$%^&*()"));


//        DesDemo demo = new DesDemo();
//        demo.test("海阔");

        try {
            Des3Demo demo3 = new Des3Demo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
