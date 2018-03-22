package com.example.reglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String PIC_URL="http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87&hs=0&pn=0&spn=0&di=113313207810&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=594559231%2C2167829292&os=2394225117%2C7942915&simid=3436308227%2C304878115&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F120727%2F201995-120HG1030762.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bpw5rtv_z%26e3Bv54AzdH3Fejvp56AzdH3Fda8da0AzdH3Fdanll9_z%26e3Bip4s&gsm=0";
    private ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImg=findViewById(R.id.img);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Glide.with(MainActivity.this).load(PIC_URL).into(mImg);
    }
}
