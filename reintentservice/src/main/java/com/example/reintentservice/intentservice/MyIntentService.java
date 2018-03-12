package com.example.reintentservice.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.reintentservice.MainActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XJ on 2018/3/2 0002.
 */

public class MyIntentService extends IntentService {
    private final String TAG=MainActivity.class.getSimpleName().toString();
   public static final String DOWNLOAD_BITMAP="load_bitmap";
   public static final String DOWNLOAD_INDEX="index";
   private static UpdateUI updateUI;
    public MyIntentService(String name) {
        super(name);
    }
    public MyIntentService(){
        super("");
    }
    public static void setUpdateUI(UpdateUI mUpdateUI){
        updateUI=mUpdateUI;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"onHandleIntent----");
         String url=intent.getStringExtra(DOWNLOAD_BITMAP);
         Bitmap bitmap=loadBitmap(url);
         Message message=Message.obtain();
         message.what=intent.getIntExtra(DOWNLOAD_INDEX,0);
         message.obj=bitmap;
         if(updateUI instanceof MainActivity){
             updateUI.doUiJob(message);
         }
    }
    public  interface UpdateUI{
        void doUiJob(Message message);
    }
    private Bitmap loadBitmap(String mapUrl){
        Bitmap bitmap=null;
        HttpURLConnection connection=null;
        BufferedInputStream inputStream=null;
        try {
            URL url=new URL(mapUrl);
            connection= (HttpURLConnection) url.openConnection();
            inputStream=new BufferedInputStream(connection.getInputStream(),8*1024);
            bitmap= BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=connection){
                connection.disconnect();
                connection=null;
            }
            if(null!=inputStream){
                try {
                    inputStream.close();
                    inputStream=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return bitmap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate----");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand----");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG,"onStart----");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind----");
        return super.onBind(intent);
    }
}
