package com.example.reretrofit2.callback;

/**
 * Created by XJ on 2018/3/17 0017.
 */

public interface CallBack<T> {
    void onFailed(Exception e);
    void onSuccess(String str);
}
