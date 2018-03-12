 package com.example.reproxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
        mTxt=findViewById(R.id.txt);
        findViewById(R.id.btn).setOnClickListener(this);

     }

     @Override
     public void onClick(View v) {
         doJob();
     }
     private void doJob(){
         Subject realSubject=new RealSubject();//创建委托对象
         ProxyHandler proxyHandler=new ProxyHandler(realSubject);//创建InvocationHandler对象
         Subject proxy= (Subject) Proxy.newProxyInstance(
                 realSubject.getClass().getClassLoader()
                 ,realSubject.getClass().getInterfaces()
                 ,proxyHandler);//生成代理对象
         proxy.request();//使用代理对象

     }

     interface Subject{
        void request();
    }
    class RealSubject implements Subject{

        @Override
        public void request() {
             mTxt.setText("干得漂亮!!!");
        }
    }
    class ProxyHandler implements InvocationHandler{
        private Subject subject;
         public ProxyHandler(Subject subject){
             this.subject=subject;
         }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result =method.invoke(subject,args);
            return result;
        }
    }
}
