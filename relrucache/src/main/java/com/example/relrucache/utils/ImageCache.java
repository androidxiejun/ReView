package com.example.relrucache.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * Created by XJ on 2018/3/19 0019.
 */

public class ImageCache extends LruCache<String,Bitmap> {
    private Map<String,SoftReference<Bitmap>>cacheMap;

    /**
     *
     * @param cacheMap
     */
    public ImageCache(Map<String,SoftReference<Bitmap>>cacheMap) {
        super((int) (Runtime.getRuntime().maxMemory()/8));
        this.cacheMap=cacheMap;
    }

    /**
     * 获取图片大小
     * @param key
     * @param value
     * @return
     */
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight();
    }

    /**
     * 当有图片从LruCache中移除时，将其放入软引用集合
     * @param evicted
     * @param key
     * @param oldValue
     * @param newValue
     */
    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if(oldValue!=null){
            SoftReference<Bitmap>softReference=new SoftReference<Bitmap>(oldValue);
            cacheMap.put(key,softReference);
        }
    }

    public Map<String,SoftReference<Bitmap>> getCacheMap(){
        return cacheMap;
    }
}
