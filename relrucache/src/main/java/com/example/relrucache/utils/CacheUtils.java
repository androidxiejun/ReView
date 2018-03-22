package com.example.relrucache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.example.relrucache.MainActivity;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XJ on 2018/3/19 0019.
 */

public class CacheUtils {
    private static Context context;
    private ImageCache imageCache;
    private static CacheUtils instance;

    public CacheUtils(Context context){
        this.context=context;
        Map<String,SoftReference<Bitmap>>cacheMap=new HashMap<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { // SDK版本判断
            this.imageCache = new ImageCache(cacheMap);
        }
    }

    public static CacheUtils getInstance(Context context){
        if(instance==null){
            synchronized (CacheUtils.class){
                if(instance==null){
                    instance=new CacheUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * 将图片保存到内存和LRUCache里面
     * @param fileName
     * @param data
     */
    private void putImgToCache(String fileName,byte[] data){
        //将图片写入到内存中
        FileUtils.getInstance(context).writeFileToStorage(fileName,data);
        //将图片存入到强引用（LruCache)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB_MR1){
            imageCache.put(fileName, BitmapFactory.decodeByteArray(data,0,data.length));
        }
    }

    /**
     * 从缓存中读取图片
     */
    public Bitmap getBitmapFromCache(String fileName,Context context){
        //从LruCache中获取图片
        Bitmap bitmap=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { // SDK版本判断
            bitmap=imageCache.get(fileName);
            Log.i(MainActivity.TAG,"2-----------------");
            if(null==bitmap){
                Log.i(MainActivity.TAG,"3-----------------");
                //当强引用中没有图片的时候，从弱引用中获取图片
                Map<String,SoftReference<Bitmap>>cacheMap=imageCache.getCacheMap();
                SoftReference<Bitmap>softReference=cacheMap.get(fileName);
                if(softReference!=null){
                    Log.i(MainActivity.TAG,"4-----------------");
                    bitmap=softReference.get();
                    imageCache.put(fileName,bitmap);
                }else{
                    Log.i(MainActivity.TAG,"5-----------------");
                    //如果软引用中也没有，则去内存中去获取
                    byte [] b=FileUtils.getInstance(context).readFileFormStorage(fileName,context);
                    if(null!=b&&b.length>0){
                        bitmap=BitmapFactory.decodeByteArray(b,0,b.length);
                        //将图片保存到LruCache
                        imageCache.put(fileName,bitmap);
                    }
                }

            }
        }
        return bitmap;
    }

    /**
     * 使用三级缓存为ImageView添加图片
     * @param path
     * @param mImg
     */
    public void setImgToView(final String path, final ImageView mImg, final Context context){
        final String fileName=path.substring(path.lastIndexOf(File.separator)+1);
        Log.i(MainActivity.TAG,"1-----------------");
//        final String fileName="test";
        Log.i(MainActivity.TAG,"fileName--"+fileName);
        Bitmap bitmap=getBitmapFromCache(fileName,context);
        if(null!=bitmap){
            Log.i(MainActivity.TAG,"6-----------------");
            mImg.setImageBitmap(bitmap);
        }else{
            Log.i(MainActivity.TAG,"7-----------------");
            //从网络获取图片
            new Thread(new Runnable() {
                @Override
                public void run() {
                    byte [] b=NetUtil.getInstance().getByteArrayFromWeb(path,context);
                    if(b!=null&&b.length>0){
                        //将图片写入缓存
                        putImgToCache(fileName,b);
                        final Bitmap bitmap1=BitmapFactory.decodeByteArray(b,0,b.length);
                        mImg.post(new Runnable() {
                            @Override
                            public void run() {
                               mImg.setImageBitmap(bitmap1);
                            }
                        });
                    }
                }
            }).start();
        }

    }

}
