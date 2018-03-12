package com.example.reasynctask.task;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reasynctask.R;

/**
 * Created by XJ on 2018/3/1 0001.
 */

public class MyTask extends AsyncTask<String,Integer,String>{
    private ProgressBar mBar;
    private TextView mTxt;
    public  MyTask(ProgressBar bar, TextView txt){
        this.mBar=bar;
        this.mTxt=txt;
    }

    /**
     * 工作在主线程中
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        for(int i=0;i<100;i++){
            publishProgress(i);//此方法可用来显示线程运行进度+ProgressBar
        }
        return "线程调用计算成功:"+strings[0];
    }

    /**
     * 此方法是配合publishProgress（），用来更新进度条,工作在主线程中
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mBar.setProgress(values[0]);
    }

    /**
     * 工作在主线程中
     * @param string
     */
    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        mTxt.setText(string);
    }
}
