package com.example.rebroadcastreceiver.ReBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;

import com.example.rebroadcastreceiver.MainActivity;

/**
 * Created by XJ on 2018/2/26 0026.
 */

public class MyBroadcast extends BroadcastReceiver {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    public void onReceive(Context context, Intent intent) {
           String action=intent.getAction();
           String data=intent.getStringExtra("data");
           if(action.equals("myaction")){
               Log.d( TAG,"tttt 消息：" + data+"   线程： " + Thread.currentThread().getName() ) ;
           }
    }
}
