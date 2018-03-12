package com.example.resingle_mode.mode;

/**
 * Created by XJ on 2018/3/4 0004.
 */

public class LazyMode {
    private static LazyMode mode ;
    public static  LazyMode getMode(){
        if(null==mode){
            return new LazyMode();
        }
        return mode;
    }
}
