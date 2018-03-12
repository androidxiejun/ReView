package com.example.reretrofit;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reretrofit.bean.ResponseBody;
import com.example.reretrofit.retrofitservice.MyServer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String API_URL = "https://api.github.com";
    private TextView mTxt;
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
        findViewById(R.id.start_btn).setOnClickListener(this);
        mTxt=findViewById(R.id.txt);
    }

    @Override
    public void onClick(View v) {
          doJob(API_URL,"square", "retrofit");
    }
    private void doJob(String url,String owner,String repo){

        //创建Retrofit对象
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).build();

        //动态生成代理;
        MyServer server=retrofit.create(MyServer.class);

        //生成一个OKHttpCall的代理对象
        Call<ResponseBody>call=server.contributors(owner,repo);

        //返回结果
        try {
            Response<ResponseBody>response=call.execute();
            Message message=Message.obtain();
            message.obj=response.body().toString();
            mHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
