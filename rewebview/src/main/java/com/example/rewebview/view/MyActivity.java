package com.example.rewebview.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.rewebview.R;

/**
 * Created by XJ on 2018/2/26 0026.
 */

public class MyActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView mWebView;
    private LinearLayout mLayout;
    private final String URL_PATH="https://www.baidu.com/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity_layout);
        initView();
    }

    private void initView() {
        mLayout=findViewById(R.id.layout);
        findViewById(R.id.my_load_web_btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addWebView();
        loadHtml2(URL_PATH);
    }

    /**
     * 从外部加载WebView
     */
    private void addWebView(){
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView=new WebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        mLayout.addView(mWebView);
    }
    /**
     * 使用WebSettings+WebViewClient辅助类辅助WebView加载网页
     * @param path
     */
    private void loadHtml2(String path){
        WebSettings webSettings=mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true);// 支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true);// 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        mWebView.loadUrl(path);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                mWebView.loadUrl(URL_PATH);
                return true;
            }
        });
    }

    /**
     * 从外部加载的WebView在界面销毁时必须手动去除WebView的资源，不然会产生内存泄漏
     */
    @Override
    protected void onDestroy() {
        if(mWebView!=null){
            mWebView.loadDataWithBaseURL(null,"","text//html","utf-8",null);
            mWebView.clearHistory();

            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();mWebView=null;
        }
        super.onDestroy();
    }
}
