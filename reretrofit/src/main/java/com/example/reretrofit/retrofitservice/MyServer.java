package com.example.reretrofit.retrofitservice;

import com.example.reretrofit.bean.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by XJ on 2018/3/4 0004.
 */

public interface MyServer {
     @GET("/repos/{owner}/{repo}/contributors")
    Call<ResponseBody>contributors(@Path("owner") String owner,@Path("repo") String repo);
}
