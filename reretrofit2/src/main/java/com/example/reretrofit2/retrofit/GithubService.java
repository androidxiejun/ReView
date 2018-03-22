package com.example.reretrofit2.retrofit;

import com.example.reretrofit2.bean.GiftBean;
import com.example.reretrofit2.bean.JavaBean;
import com.example.reretrofit2.callback.CallBack;

/**
 * Created by XJ on 2018/3/17 0017.
 */

public interface GithubService {
   @GET("/repos/{owner}/retrofit/contributors")
    void listRepos(@Path("owner") String owner, CallBack<JavaBean>callBack);

   @GET("/search/skus?query=%E8%BF%9E%E8%A1%A3%E8%A3%99++&sort=all&ga=%2Fsearch%2Fskus&flag=&cat=&")
    GiftBean listGift(@Path("asc") String asc, CallBack<GiftBean>callBack);
}
