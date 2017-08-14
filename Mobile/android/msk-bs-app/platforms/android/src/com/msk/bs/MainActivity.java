/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.msk.bs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import org.apache.cordova.*;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends CordovaActivity
{
    private WebView mwebview;
    private int press = 0;
    Timer timer;
    public void timerPress(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTestTask(), seconds*1000);
    }
    class TimerTestTask extends TimerTask {
        public void run() {
            press = 0;
            timer.cancel();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mwebview = (WebView) findViewById(R.id.webview);
//        mwebview.getSettings().setJavaScriptEnabled(true);
//        mwebview.setWebChromeClient(new WebChromeClient());
//        mwebview.addJavascriptInterface(new JSInterface1(), "injs");
        //mwebview.loadUrl(launchUrl);
        loadUrl(launchUrl);
    }


    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(press == 0) {
                press ++ ;
                loadUrl("javascript: eventBacksButton()");
            }else{
                finish();
            }
            timerPress(2);
        }
        return false;
    }

   /* class JSInterface1{
        //JavaScript调用此方法
        @JavascriptInterface
        public void callAndroidMethod(){
            finish();
        }
    }*/
}
