package presenter;

import android.content.Intent;

import com.example.rxjava_retrofit_mvp2.view.View;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到

}
