package com.example.rebinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView mTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        int datas[]={1,3,22,54,11,15,13,56,89,3,6,2,37};
        datas=doJob(datas,datas.length);
        mTxt.setText(Arrays.toString(datas));
    }

    private void initView() {
        mTxt=findViewById(R.id.txt);
    }

    private int[] doJob(int [] data,int n){
        int i,k=n;
        boolean flag=true;
        while(flag){
            flag=false;
            for(i=1;i<k;i++){
                if(data[i-1]>data[i]){
                    int temp=data[i-1];
                    data[i-1]=data[i];
                    data[i]=temp;
                    flag=true;
                }
            }
            k--;
        }
        return data;
    }
}
