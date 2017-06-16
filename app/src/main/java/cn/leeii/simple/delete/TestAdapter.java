package cn.leeii.simple.delete;

import android.content.Context;
import android.view.View;

import cn.leeii.libmvp.widget.recycler.BaseAdapter;
import cn.leeii.simple.R;

/**
 * Created by Lee on 2016/12/9.
 */

public class TestAdapter extends BaseAdapter {
    private int count;

    public TestAdapter(Context context) {
        super(context, R.layout.layout_test_item);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected int getCount() {
        return count;
    }

    @Override
    protected void onBindHolder(ViewHolder holder, int position) {
    }

    @Override
    public ViewHolder newHolder(View itemView, int itemViewType) {
        return new ViewHolder(itemView);
    }
}
