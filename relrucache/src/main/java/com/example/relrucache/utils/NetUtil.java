package com.example.relrucache.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XJ on 2018/3/19 0019.
 */

public class NetUtil {
    private static NetUtil instance;
    public NetUtil(){

    }
    public static NetUtil getInstance(){
        if(instance==null){
            synchronized (NetUtil.class){
                if(instance==null){
                    instance=new NetUtil();
                }
            }
        }
        return instance;
    }
    public byte[] getByteArrayFromWeb(String path, Context context){
        byte [] b=null;
        InputStream is=null;
        ByteArrayOutputStream baos=null;
        try {
            URL url=new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setReadTimeout(8*1000);
            connection.setConnectTimeout(5*1000);
            connection.connect();
            if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                baos=new ByteArrayOutputStream();
                is=connection.getInputStream();
                byte [] temp=new byte[1024];
                int length=0;
                while((length=is.read(temp))!=-1){
                    baos.write(temp,0,length);
                }
            }
            b=baos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }
}
