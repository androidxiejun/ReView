package view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rxjava_retrofit_mvp2.R;
import com.example.rxjava_retrofit_mvp2.view.BookView;

import module.Book;
import presenter.BookPresenter;
import view.BaseActivity;

public class MainActivity extends BaseActivity implements BookView {
    private TextView mTxt;
    private Button mBtn;
    private BookPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mTxt=F(R.id.txt);
        mBtn=F(R.id.btn);
        C(mBtn);
        presenter=new BookPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void processClick(View view) {
        Log.d("JAVA","11111111");
        presenter.getSearchBook("金瓶梅",null,0,1);
    }

    @Override
    public void onSuccess(Book mBook) {
             mTxt.setText(mBook.getBooks().get(0).getAlt_title());
    }

    @Override
    public void onErro(String result) {

    }
}
