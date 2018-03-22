package com.example.relrucache.utils;

import android.content.Context;
import android.util.Log;

import com.example.relrucache.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by XJ on 2018/3/19 0019.
 */

public class FileUtils {
    private static FileUtils instance;
    private static Context context;
    public FileUtils(Context context){
        this.context=context;
    }
    public static FileUtils getInstance(Context context){
        if(instance==null){
            synchronized (FileUtils.class){
                if(instance==null){
                    instance=new FileUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * 将文件写入内存中
     */
    public void writeFileToStorage(String fileName,byte []b){
        FileOutputStream fos=null;
        try {
            String parent=context.getFilesDir().getAbsolutePath();
            Log.i(MainActivity.TAG,"parent--------"+parent);
            File file=new File(context.getFilesDir(),fileName);

            fos=new FileOutputStream(file);
            fos.write(b,0,b.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从内存中读取文件的字节码s
     * @param fileName
     * @return
     */
    public byte [] readFileFormStorage(String fileName,Context context){
        byte [] b=null;
        FileInputStream fis=null;
        ByteArrayOutputStream baos=null;
        try {
            fis=context.openFileInput(fileName);
            baos=new ByteArrayOutputStream();
            byte []temp=new byte[1024];
            int length=0;
            while((length=fis.read())!=-1){
                baos.write(temp,0,length);
            }
            b=baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }
}
