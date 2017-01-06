package cn.leeii.simple.delete;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import cn.leeii.lib.widget.recycler.BaseAdapter;
import cn.leeii.lib.widget.recycler.listener.LoadMoreListener;
import cn.leeii.lib.widget.recycler.listener.RecyclerListener;
import cn.leeii.simple.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TestAdapter mAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.webView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdater = new TestAdapter(this));
        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 500));
        view.setBackgroundColor(Color.BLUE);
        mAdater.setHeaderView(view);
        mAdater.setOnEmptyButtonClickListener(new BaseAdapter.OnEmptyButtonClickListener() {
            @Override
            public void onEmptyButtonClick() {
                pretendingToBeLoading();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mAdater.isLoad())
                    pretendingToBeLoading();
            }
        }));
    }

    public void button(View view) {
        switch (view.getId()) {
            case R.id.button0:
                mAdater.changeDisplayStatus(BaseAdapter.STATE_LOADING);
                break;
            case R.id.button1:
                mAdater.changeDisplayStatus(BaseAdapter.STATE_CONTENT);
                break;
            case R.id.button2:
                mAdater.changeDisplayStatus(BaseAdapter.STATE_EMPTY);
                break;
        }
    }

    private void pretendingToBeLoading() {
        Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                subscriber.onNext((int) (Math.random() * 10));
                subscriber.onNext(20);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer s) {
                        mAdater.setCount(mAdater.getCount() + s);
                        mAdater.notifyDataSetChanged();
                        if (s == 0) mAdater.changeDisplayStatus(BaseAdapter.STATE_EMPTY);
                        else
                            mAdater.changeDisplayStatus(BaseAdapter.STATE_CONTENT);
                        mAdater.setLoad(mAdater.getCount() + s < 1000);
                    }
                });

    }
}
