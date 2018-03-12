package view;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected  <E extends View> E F(int resId){
        return (E) findViewById(resId);
    }

    protected <E extends View> void C(View view){
        view.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        processClick(v);
    }
    protected abstract void processClick(View view);
 }
