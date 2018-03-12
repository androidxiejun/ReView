package com.example.resingle_mode.mode;

/**
 * Created by XJ on 2018/3/4 0004.
 */

public class LazySafeMode {
    private static LazySafeMode mode;

    /**
     * 加上synchronized关键字，让其成为线程安全的类
     * @return
     */
    public static synchronized LazySafeMode getInstance(){
        if(null==mode){
            return new LazySafeMode();
        }
         return mode;
    }

    /**
     * 与上一种方式稍微有点不同
     * @return
     */
    public static LazySafeMode getInstance2(){
        synchronized (LazySafeMode.class){
            if(null==mode){
                return new LazySafeMode();
            }
        }
        return mode;

    }
}
