package com.example.reokhttp;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTxt;
    public static final String URL="http://www.baidu.com";
    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mTxt.setText((String) msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        mTxt=findViewById(R.id.txt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                new Thread(new MyRunnable_GET_TB()).start();
                break;
            case R.id.btn2:
                new Thread(new MyRunnable_GET_Async()).start();
                break;
            case R.id.btn3:
                new Thread(new MyRunnable_POST_TB()).start();
                break;
            case R.id.btn4:
                new Thread(new MyRunnable_POST_Async()).start();
                break;
        }

    }

    /**
     * OKhttp_GET同步请求
     */
    class MyRunnable_GET_TB implements Runnable {

        @Override
        public void run() {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(URL).build();
            Response response=null;
            try {
                response=client.newCall(request).execute();
                if(response.isSuccessful()){
                   Message message=Message.obtain();
                   message.obj="GET同步请求承成功";
                   mHandler.sendMessage(message);
                }else {
                    Message message=Message.obtain();
                    message.obj="GET同步请求承失败";
                    mHandler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * OkHttp_GET异步请求
     */
    class MyRunnable_GET_Async implements Runnable{

        @Override
        public void run() {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(URL).build();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Message message=Message.obtain();
                    message.obj="GET异步请求失败";
                    mHandler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message=Message.obtain();
                    message.obj="GET异步请求成功";
                    mHandler.sendMessage(message);
                }
            });
        }
    }

    /**
     * OkHttp_POST同步请求
     */
    class MyRunnable_POST_TB implements Runnable{

        @Override
        public void run() {
            OkHttpClient client=new OkHttpClient();
            FormBody.Builder formBody=new FormBody.Builder();
            formBody.add("name","xiejun");
            Request request=new Request.Builder().url(URL).post(formBody.build()).build();
            Response response=null;
            try {
                response=client.newCall(request).execute();
                if(response.isSuccessful()){
                    Message message=Message.obtain();
                    message.obj="POST同步请求成功";
                    mHandler.sendMessage(message);
                }else {
                    Message message=Message.obtain();
                    message.obj="POST同步请求失败";
                    mHandler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * OkHttp_POST异步请求
     */
    class MyRunnable_POST_Async implements Runnable{

        @Override
        public void run() {
            OkHttpClient client=new OkHttpClient();
            FormBody.Builder formBody=new FormBody.Builder();//创建表单请求体
            formBody.add("username","zhangsan");//传递键值对参数
            Request request=new Request.Builder().url(URL).post(formBody.build()).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message=Message.obtain();
                    message.obj="POST异步请求失败";
                    mHandler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message=Message.obtain();
                    message.obj="POST异步请求成功";
                    mHandler.sendMessage(message);
                }
            });
        }
    }
}
