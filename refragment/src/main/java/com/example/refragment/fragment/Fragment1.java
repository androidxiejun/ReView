package com.example.refragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.refragment.MainActivity;
import com.example.refragment.R;

/**
 * Created by XJ on 2018/2/24 0024.
 */

public class Fragment1 extends Fragment implements View.OnClickListener{
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private Button mBtn;
    private TextView mTxt;
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        Log.i(TAG,"fragment1---onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"fragment1---onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"fragment1---onCreateView");
        View view= inflater.inflate(R.layout.fragment1_layout,container,false);
        mBtn=view.findViewById(R.id.fragment_btn1);
        mTxt=view.findViewById(R.id.fragment_txt1);
        mBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"fragment1---onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"fragment1---onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"fragment1---onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"fragment1---onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"fragment1---onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"fragment1---onDestroy");
    }

    @Override
    public void onClick(View v) {
        //获取MainActivity实例，并实现其方法--Fragment与Activity进行通信
        String name=((MainActivity)getActivity()).getName();
        mTxt.setText(name);
        if(mContext instanceof onCallBack){
            ((onCallBack) mContext).succ();
        }
    }

    /**
     * 回调方法，用于Activity与Fragment进行通信
     */
    public interface onCallBack{
        void succ();
        void erro();
    }
}
