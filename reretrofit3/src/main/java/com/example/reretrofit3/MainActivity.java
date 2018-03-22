package com.example.reretrofit3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.reretrofit3.dynamic_proxy.MyInvocationHandler;
import com.example.reretrofit3.dynamic_proxy.RealSubject;
import com.example.reretrofit3.dynamic_proxy.Subject;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        mTxt=findViewById(R.id.txt);
    }

    @Override
    public void onClick(View v) {
        Subject subject=new RealSubject();
        MyInvocationHandler handler=new MyInvocationHandler(subject);
        Subject sub= (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),subject.getClass().getInterfaces(),handler);
        mTxt.setText(sub.getMsg());
    }
}
