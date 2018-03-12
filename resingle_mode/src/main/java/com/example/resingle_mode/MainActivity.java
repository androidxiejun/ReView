package com.example.resingle_mode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.resingle_mode.mode.LazyMode;
import com.ums.AppHelper;

public class MainActivity extends AppCompatActivity {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name=AppHelper.getBaseSysInfo(this);
        Log.i(TAG,"name="+name);
        new MyThread().doJob();
    }


    class MyThread extends Thread {
        private void doJob() {
            MyThread[] ets=new MyThread[10];
            for (int i=0;i<ets.length;i++){
                ets[i]=new MyThread();
            }
            for (int i=0;i<ets.length;i++){
                ets[i].start();
            }
        }
        @Override
        public void run() {
            Log.i(TAG, String.valueOf(LazyMode.getMode().hashCode()));
        }
    }

}
