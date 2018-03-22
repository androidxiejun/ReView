package com.example.testupdateapp.service;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import com.example.testupdateapp.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XJ on 2018/3/21 0021.
 */

public class MyServicee extends Service {
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showDialog();//这里只负责弹出Dialog
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(MainActivity.TAG,"onCreate-----");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MainActivity.TAG,"onStartCommand-----");
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                new Thread(new MyRunnable()).start();
            }
        };
        timer.schedule(timerTask,5*1000,40600*1000);//每半天检查一次
        return super.onStartCommand(intent, flags, startId);
    }
    class MyRunnable implements Runnable{

        @Override
        public void run() {
            while(true){
                PackageManager pckMan = getApplicationContext().getPackageManager();
                ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
                List<PackageInfo> infoList = pckMan.getInstalledPackages(0);
                 for (PackageInfo packageInfo:infoList){
                     String pkgName=packageInfo.packageName;
                     if(pkgName.equals("com.intime.mjpos")){
                         String versionName=packageInfo.versionName;
                         Log.i(MainActivity.TAG,"vrsionName----"+versionName);
                         Looper.prepare();
                         showDialog();
                         Looper.loop();
//                         return;
                     }
                 }

            }
        }
    }
  private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("发现新版程序")
                .setMessage("是否立即升级")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                               new Thread(new MyRunnable()).start();
                    }
                })
                .setNegativeButton("稍后提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHandler.sendEmptyMessageDelayed(0,5*1000);
                    }
                });
      AlertDialog dialog=builder.create();
        //将dialog指定为系统Dialog
      dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
      dialog.show();

  }
}
