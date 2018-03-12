package presenter;

import android.content.Intent;
import android.util.Log;

import com.example.rxjava_retrofit_mvp2.view.BookView;
import view.MainActivity;
import com.example.rxjava_retrofit_mvp2.view.View;

import module.Book;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.RetrofitHelper;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class BookPresenter implements Presenter{
    private Observable<Book>observable;
    private BookView mBookView;
    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        if(view instanceof MainActivity)
         mBookView= (BookView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchBook(String name, String tag, int start, int count){
        Log.d("JAVA","2222222");
        observable= RetrofitHelper.getService(name,tag,start,count);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                      mBookView.onErro(e.getMessage());
                    }

                    @Override
                    public void onNext(Book book) {
                         mBookView.onSuccess(book);
                    }
                });
    }
}
