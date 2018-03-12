package com.example.rewebview;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rewebview.view.MyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView mWebView;
    private final String URL_PATH="https://www.baidu.com/";
    private final String TAG=MainActivity.class.getSimpleName().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.load_web_btn1).setOnClickListener(this);
        findViewById(R.id.load_web_btn2).setOnClickListener(this);
        findViewById(R.id.load_web_btn3).setOnClickListener(this);

        mWebView=findViewById(R.id.web_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_web_btn1:
                loadHtml(URL_PATH);
                break;
            case R.id.load_web_btn2:
                loadHtml2(URL_PATH);
                break;
            case R.id.load_web_btn3:
                Intent intent=new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 使用默认浏览器打开网页
     * @param path
     */
    private void loadHtml(String path){
        mWebView.loadUrl(path);
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
                //优先使用缓存:
                view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                //缓存模式如下：
                // LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
                // LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
                // LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
                // LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
                mWebView.loadUrl(URL_PATH);
                return true;
            }
        });
    }
}
