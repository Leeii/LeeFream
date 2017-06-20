package com.leeiidesu.libmvp.widget.recycler.listener;

import android.view.View;

/**
 * Created by Lee on 2016/12/9.
 */

public interface ItemClickListener {
    /**
     * RecyclerView点击监听
     *
     * @param v        布局View
     * @param position 索引角标
     */
    void onRecyclerItemClick(View v, int position);
}
