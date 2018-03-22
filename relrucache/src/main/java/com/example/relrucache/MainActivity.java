package com.example.relrucache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.relrucache.utils.CacheUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG=MainActivity.class.getSimpleName().toString();
    private ImageView mImg;
//    private final String PATH="http://s0.mingxingyichu.cn/group6/M00/B9/79/wKgBjFXxXCKAYGiAAAAEURJy0M4061.png";
    private final String PATH="http://s0.mingxingyichu.cn/group6/M00/B9/79/wKgBjFXxXCKAYGiAAAAEURJy0M4061.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        mImg=findViewById(R.id.img);
    }

    @Override
    public void onClick(View v) {
        CacheUtils.getInstance(MainActivity.this).setImgToView(PATH,mImg,MainActivity.this);
    }
}
