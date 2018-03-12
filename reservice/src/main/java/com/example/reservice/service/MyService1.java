package com.example.reservice.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.reservice.MainActivity;

/**
 * Created by XJ on 2018/2/25 0025.
 */

public class MyService1 extends Service {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private int index;
    public class MyBinder extends Binder{
        public MyService1 getService(){
            return MyService1.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MyService1---onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"MyService1---onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"MyService1---onStartCommand");
        new Thread(new MyRunnable()).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MyService1---onDestroy");
    }
    public void doSonmeThing(){
        while (true){
            Log.i(TAG,"doSonmeThing--"+index++);
        }
    }
    class  MyRunnable implements Runnable {

        @Override
        public void run() {
            doSonmeThing();
        }
    }
}
