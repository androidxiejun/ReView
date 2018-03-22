package com.example.reretrofit3.dynamic_proxy;

/**
 * Created by XJ on 2018/3/18 0018.
 */

public class RealSubject implements Subject {
    @Override
    public String getMsg() {
        return "this is the dynamic_proxy";
    }
}
