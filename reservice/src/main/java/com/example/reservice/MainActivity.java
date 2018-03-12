package com.example.reservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.reservice.service.MyService1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private MyService1 myService1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.bind_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_btn:
                Intent intent=new Intent(MainActivity.this, MyService1.class);
                startService(intent);
                break;
            case R.id.bind_btn:
                Intent intent1=new Intent(MainActivity.this,MyService1.class);
                bindService(intent1,connection,BIND_AUTO_CREATE);
                break;
        }
    }
    ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"bindService---onServiceConnected");
            myService1=( (MyService1.MyBinder)service).getService();
//            myService1.doSonmeThing();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"bindService---onServiceDisconnected");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
