package com.example.reretrofit2.retrofit;

import android.util.Log;

import com.example.reretrofit2.bean.GiftBean;
import com.example.reretrofit2.bean.JavaBean;
import com.example.reretrofit2.callback.CallBack;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by XJ on 2018/3/17 0017.
 */

public class Retrofit {
    public static final String TAG=Retrofit.class.getSimpleName().toString();
    private String baseUrl;
    private Retrofit(Builder builder){
           this.baseUrl=builder.baseUrl;

    }
    public static class Builder{
        private String baseUrl;
        public Builder baseUrl(String host){
            this.baseUrl=host;
            return this;
        }
        public Retrofit build(){
            return new Retrofit(this);
        }

    }
    public <T> T create(Class<T> clazz){
        Object o= Proxy.newProxyInstance(Retrofit.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //首先会的回调函数
                final CallBack<?>callBack=(CallBack<?>) args[args.length - 1];
                final GET get=method.getAnnotation(GET.class);
                if(null!=get){
                    //获取GET注解的值
                    String getValue=get.value();
                    Log.i(TAG,"value-----"+getValue);

                    Annotation[][] methodParameterAnnotationArrays=method.getParameterAnnotations();
                    if(null!=methodParameterAnnotationArrays){
                        int count=methodParameterAnnotationArrays.length;
                        for(int i=0;i<count;i++){
                            //获取单个参数上的注解
                            Annotation []methodParameterAnnotations=methodParameterAnnotationArrays[i];
                            if(null!=methodParameterAnnotations){
                                for(Annotation methodParameterAnnotation:methodParameterAnnotations){
                                    //如果是Path注解
                                    if(methodParameterAnnotation instanceof Path){
                                        //取得Path上对应的值
                                        Path path= (Path) methodParameterAnnotation;
                                        String pathValue=path.value();
                                        Log.i(TAG,"pathValue----"+pathValue);

                                        Request.Builder builder=new Request.Builder();

                                        Log.i(TAG,"对应参数的值------"+args[i]);

                                        //使用Path注解替换GET注解中的值
                                        String result=getValue.replaceAll("\\{" + pathValue + "\\}", (String) args[i]);

                                        Log.i(TAG,"result-----"+result);

                                        Request request=builder.get().url(baseUrl+"/"+result).build();
                                        Log.i(TAG,"3333333333");

                                        OkHttpClient okHttpClient=new OkHttpClient();
                                        Log.i(TAG,"4444444444");
//                                        okHttpClient.newCall(request).enqueue(new Callback() {
//                                            @Override
//                                            public void onFailure(Request request, IOException e) {
//                                                Log.i(TAG,"5555555555");
//                                                 callBack.onFailed(e);
//                                            }
//
//                                            @Override
//                                            public void onResponse(Response response) throws IOException {
//                                                Log.i(TAG,"66666666666");
//                                                int code=response.code();
//                                                Log.i(TAG,"code----"+code);
//                                                boolean isOk=response.isSuccessful();
//                                                Log.i(TAG,"isSuccess--"+isOk);
//                                                if(response.isSuccessful()){
//                                                    Log.i(TAG,"777777777");
//                                                    String body=response.body().string();
////                                                    Log.i(TAG,"88888888888--body="+body);
////                                                    Object o1= new Gson().fromJson(body, JavaBean.class);
////                                                    Log.i(TAG,"99999999999");
//                                                    callBack.onSuccess(body);
//                                                }else{
//                                                    callBack.onFailed(new Exception("code--"+code));
//                                                }
//
//                                            }
//                                        });
                                        Response response=okHttpClient.newCall(request).execute();
                                        if(response.message().equals("OK")){
                                           callBack.onSuccess(response.body().string());
                                        }else{
                                            callBack.onFailed(new Exception("未知错误  "));
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
                return null;
            }
        });
        return (T)o;
    }


}
