package com.example.reintentservice;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.reintentservice.intentservice.MyIntentService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyIntentService.UpdateUI{
    private ImageView mImg;
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private String url[] = {
            "http://img.blog.csdn.net/20160903083245762",
            "http://img.blog.csdn.net/20160903083252184",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083257871",
            "http://img.blog.csdn.net/20160903083311972",
            "http://img.blog.csdn.net/20160903083319668",
            "http://img.blog.csdn.net/20160903083326871"
    };
    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG,"handler---");
            Bitmap bitmap= (Bitmap) msg.obj;
            mImg.setImageBitmap(bitmap);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImg=findViewById(R.id.img);
        findViewById(R.id.load_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"onCLick---");
        Intent intent=new Intent(MainActivity.this,MyIntentService.class);
        Log.i(TAG,"onCLick22222---");
        for (int i=0;i<7;i++) {//循环启动任务
            intent.putExtra(MyIntentService.DOWNLOAD_BITMAP,url[i]);
            intent.putExtra(MyIntentService.DOWNLOAD_INDEX,i);
            startService(intent);
        }
        MyIntentService.setUpdateUI(this);
    }
    //必须通过Handler去更新，该方法为异步方法，不可更新UI
    @Override
    public void doUiJob(Message message) {
        Log.i(TAG,"doUiJob---main");
        mHandler.sendMessage(message);
    }
}
