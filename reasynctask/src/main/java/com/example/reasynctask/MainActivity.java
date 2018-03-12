package com.example.reasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reasynctask.task.MyTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mTxt;
    private ProgressBar mBar;
    private MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        mTxt=findViewById(R.id.txt);
        mBar=findViewById(R.id.progress_bar);

    }
    private void doJob(){
        myTask=new MyTask(mBar,mTxt);
        myTask.execute("text");
    }

    @Override
    public void onClick(View v) {
        doJob();
    }
}
