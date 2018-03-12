package com.example.refragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.refragment.MainActivity;
import com.example.refragment.R;

/**
 * Created by XJ on 2018/2/24 0024.
 */

public class Fragment4 extends Fragment {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"fragment4---onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"fragment4---onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"fragment4---onCreateView");
        View view= inflater.inflate(R.layout.fragment4_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"fragment4---onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"fragment4---onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"fragment4---onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"fragment4---onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"fragment4---onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"fragment4---onDestroy");
    }
}
