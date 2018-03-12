package utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import module.Book;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class RetrofitHelper {
    private static final String BASE_URL="https://api.douban.com/v2/";

    /**
     * 用于获取Observable对象
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     */
    public static Observable<Book> getService(String name, String tag, int start, int count){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RetrofitService service=retrofit.create(RetrofitService.class);
        return  service.getSearchBook(name,tag,start,count);
    }
}
