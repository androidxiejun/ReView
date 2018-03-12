package com.example.resingle_mode.mode;

/**
 * Created by XJ on 2018/3/4 0004.
 */

public class StaticLBLMode {
    public static StaticLBLMode getInstance(){
        return SingleMode.instance;
    }

    /**
     * 静态内部类
     */
    private static class SingleMode{
        private static final StaticLBLMode instance=new StaticLBLMode();
    }
}
