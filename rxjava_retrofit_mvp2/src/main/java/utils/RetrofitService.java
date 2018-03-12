package utils;

import module.Book;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBook(@Query("q") String name, @Query("tag") String tag,
                                   @Query("start") int start, @Query("count") int count);
}
