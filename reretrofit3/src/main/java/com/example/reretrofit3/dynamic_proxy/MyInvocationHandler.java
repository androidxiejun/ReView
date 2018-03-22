package com.example.reretrofit3.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by XJ on 2018/3/18 0018.
 */

public class MyInvocationHandler implements InvocationHandler {
    protected  Subject subject;
    public MyInvocationHandler(Subject subject){
        this.subject=subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(subject,args);
    }
}
