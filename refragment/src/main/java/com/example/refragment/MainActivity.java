package com.example.refragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.refragment.adapter.MyAdapter;
import com.example.refragment.fragment.Fragment1;
import com.example.refragment.fragment.Fragment2;
import com.example.refragment.fragment.Fragment3;
import com.example.refragment.fragment.Fragment4;
import com.example.refragment.fragment.Fragment5;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener,Fragment1.onCallBack {
    private final String TAG=MainActivity.class.getSimpleName().toString();
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private List<Fragment>fragmentList;
    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"activity---onCreate");
        setContentView(R.layout.activity_main);
//        initFragment();//单独加载某个Fragment
        initFragmentData();//Fragment+ViewPager
    }

    /**
     * 初始化Fragment+ViewPager+RadioGroup
     */
    private void initFragmentData() {
        fragmentList=new ArrayList<>();
         mViewPager=findViewById(R.id.view_pager);
         mRadioGroup=findViewById(R.id.radio_group);
         fragment1=new Fragment1();
         fragment2=new Fragment2();
         fragment3=new Fragment3();
         fragment4=new Fragment4();
         fragmentList.add(fragment1);
         fragmentList.add(fragment2);
         fragmentList.add(fragment3);
         fragmentList.add(fragment4);
         mAdapter=new MyAdapter(getSupportFragmentManager(),fragmentList);
         mViewPager.setAdapter(mAdapter);
         mRadioGroup.check(R.id.radio_btn1);
         mRadioGroup.setOnCheckedChangeListener(this);
         mViewPager.setOnPageChangeListener(this);


    }

    /**
     * Activity单独加载一个Fragment
     */
    private void initFragment() {
        fragment5=new Fragment5();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,fragment5,"fragment1");
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"activity---onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"activity---onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"activity---onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"activity---onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"activity---onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"activity---onDestroy");
    }

    /**
     * 监听RadioGroup的点击事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radio_btn1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.radio_btn2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.radio_btn3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.radio_btn4:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    /**
     * 监听ViewPager滑动时间
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                mRadioGroup.check(R.id.radio_btn1);
                break;
            case 1:
                mRadioGroup.check(R.id.radio_btn2);
                break;
            case 2:
                mRadioGroup.check(R.id.radio_btn3);
                break;
            case 3:
                mRadioGroup.check(R.id.radio_btn4);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public  String  getName(){
        return "XJ";
    }

    @Override
    public void succ() {
        Toast.makeText(this, "通信成功", Toast.LENGTH_SHORT).show();
        fragment2.changeName();
    }

    @Override
    public void erro() {
        Toast.makeText(this, "通信失败", Toast.LENGTH_SHORT).show();
    }
}
