package cn.leeii.simple.ui.ref;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.leeiidesu.lib.widget.adapter.OnItemClickListener;

import butterknife.BindView;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.main.adapter.MainAdapter;
import cn.leeii.simple.ui.ref.di.DaggerRefreshTestComponent;
import cn.leeii.simple.ui.ref.di.RefreshTestModule;

/**
 * Created by leeiidesu on 2017/6/30.
 */
public class RefreshTestActivity extends BaseActivity<RefreshTestPresenter> implements RefreshTestContract.IRefreshTestView {


    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter();

        recycler.setAdapter(mainAdapter);

        mainAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Log.e("TAG", "position = " + position);
            }
        });

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_refresh_test;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        DaggerRefreshTestComponent
                .builder()
                .baseComponent(baseComponent)
                .refreshTestModule(new RefreshTestModule(this))
                .build()
                .inject(this);
    }


}
