package com.example.revolatile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int index;
    private TextView mTxt;
    public static final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        doIt();
    }

    private void initView() {
       mTxt= findViewById(R.id.txt);
    }

    private void doIt() {
        synchronized ((Object)index){

            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            increase();
                    };
                }.start();
            }
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        Log.i(TAG,"index===="+index);
    }

    private void increase(){
        index++;
        Log.i(TAG,"---------"+index);
    }
}
