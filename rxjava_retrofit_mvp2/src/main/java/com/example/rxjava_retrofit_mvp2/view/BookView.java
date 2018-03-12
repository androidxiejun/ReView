package com.example.rxjava_retrofit_mvp2.view;


import android.view.*;

import module.Book;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public interface BookView extends View {
    void onSuccess(Book mBook);
    void onErro(String result);
}
