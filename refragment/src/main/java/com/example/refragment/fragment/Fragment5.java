package com.example.refragment.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.refragment.MainActivity;
import com.example.refragment.R;

/**
 * Created by XJ on 2018/2/24 0024.
 */

public class Fragment5 extends Fragment {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"fragment5---onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"fragment5---onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"fragment5---onCreateView");
        View view=inflater.inflate(R.layout.fragment1_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"fragment5---onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"fragment5---onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"fragment5---onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"fragment5---onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"fragment5---onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"fragment5---onDestroy");
    }
}
