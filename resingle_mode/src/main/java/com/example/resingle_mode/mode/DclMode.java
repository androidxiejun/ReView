package com.example.resingle_mode.mode;

/**
 * Created by XJ on 2018/3/4 0004.
 */

public class DclMode {
    private static volatile DclMode mode;
    public static  DclMode getInstance(){
        //避免不必要的同步
        if(null==mode){
            //同步
            synchronized (DclMode.class){
                if(null==mode){
                    return new DclMode();
                }
            }
        }
        return mode;
    }
}
