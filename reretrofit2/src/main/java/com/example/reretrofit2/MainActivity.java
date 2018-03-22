package com.example.reretrofit2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reretrofit2.bean.GiftBean;
import com.example.reretrofit2.bean.JavaBean;
import com.example.reretrofit2.callback.CallBack;
import com.example.reretrofit2.retrofit.GithubService;
import com.example.reretrofit2.retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTxt;
    public static final String TAG=MainActivity.class.getSimpleName().toString();
    private final String HOST_URL="https://api.github.com";
    private final String HOST_URL2="http://api-v2.mall.hichao.com/search/skus?query=%E8%BF%9E%E8%A1%A3%E8%A3%99++&sort=all&ga=%2Fsearch%2Fskus&flag=&cat=&asc=1";

    @SuppressLint("HandlerLeak")
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(MainActivity.this, "获取数据成功", Toast.LENGTH_SHORT).show();
                    String str= (String) msg.obj;
                    mTxt.setText(str);
                    break;
                case 2:
                    String result= (String) msg.obj;
                    mTxt.setText(result);
//                    Toast.makeText(MainActivity.this, "获取数据失败---"+result, Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mTxt=findViewById(R.id.txt);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          new Thread(new MyRunnable()).start();
    }
    class MyRunnable implements Runnable{

        @Override
        public void run() {
             getMSsg();
        }
    }
    private void getMSsg(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(HOST_URL2).build();
        GithubService githubService=retrofit.create(GithubService.class);
        githubService.listGift("asc", new CallBack<GiftBean>() {
            @Override
            public void onFailed(Exception e) {
                Message message=Message.obtain();
                message.what=2;
                message.obj=e.getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onSuccess(String str) {
                Message message=Message.obtain();
                message.what=1;
                message.obj=str;
                mHandler.sendMessage(message);
            }
        });
    }
}
