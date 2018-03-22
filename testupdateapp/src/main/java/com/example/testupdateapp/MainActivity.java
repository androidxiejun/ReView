package com.example.testupdateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testupdateapp.service.MyServicee;

public class MainActivity extends AppCompatActivity {
    public static final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this, MyServicee.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Intent intent=new Intent(MainActivity.this,MyServicee.class);
//        stopService(intent);
    }
}
