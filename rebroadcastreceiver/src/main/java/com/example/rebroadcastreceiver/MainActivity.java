package com.example.rebroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rebroadcastreceiver.ReBroadcast.MyBroadcast;
import com.example.rebroadcastreceiver.ReBroadcast.MyDTBroatcast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private MyBroadcast myBroadcast;
    private MyDTBroatcast myDTBroatcast;
    private LocalBroadcastManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        findViewById(R.id.register_btn).setOnClickListener(this);
        findViewById(R.id.register_jt_btn).setOnClickListener(this);
        findViewById(R.id.register_dt_btn).setOnClickListener(this);

    }

    /**
     * 绑定本地广播
     */
    private void registerRecerver() {
        myBroadcast=new MyBroadcast();
        manager=LocalBroadcastManager.getInstance(this);
        IntentFilter filter=new IntentFilter("myaction");
        manager.registerReceiver(myBroadcast,filter);
        sendData();
    }

    /**
     * 绑定、发送静态广播
     * 静态广播：直接在manafest里面注册广播，广播不能随android进程销毁而销毁
     */
    private void registerJTReceiver(){
         Intent intent=new Intent();
         intent.setAction("com.xj.action");
         intent.putExtra("data","绑定静态广播");
         sendBroadcast(intent);
    }

    /**
     * 绑定、发送动态广播
     */
    private void regisetrDTReceiver(){
         myDTBroatcast=new MyDTBroatcast();
         IntentFilter filter=new IntentFilter("com.test.action");
         registerReceiver(myDTBroatcast,filter);
         Intent intent=new Intent("com.test.action");
         intent.putExtra("data","绑定动态广播");
         sendBroadcast(intent);
    }
    /**
     * 在主线程发送广播
     */
    private void sendData() {
        Intent intent=new Intent("myaction");
        intent.putExtra("data","主线程消息");
        manager.sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                registerRecerver();
                break;
            case R.id.register_jt_btn:
                registerJTReceiver();
                break;
            case R.id.register_dt_btn:
                regisetrDTReceiver();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(myBroadcast);
        unregisterReceiver(myDTBroatcast);
    }
}
