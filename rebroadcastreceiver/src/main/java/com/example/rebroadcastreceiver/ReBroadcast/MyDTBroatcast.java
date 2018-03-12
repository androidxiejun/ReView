package com.example.rebroadcastreceiver.ReBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.rebroadcastreceiver.MainActivity;

/**
 * Created by XJ on 2018/2/26 0026.
 */

public class MyDTBroatcast extends BroadcastReceiver {
    private final String TAG=MainActivity.class.getSimpleName().toString();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.test.action")){
            String data=intent.getStringExtra("data");
            Log.d( TAG,"tttt 消息：" + data+"     线程： " + Thread.currentThread().getName() ) ;
        }
    }
}
