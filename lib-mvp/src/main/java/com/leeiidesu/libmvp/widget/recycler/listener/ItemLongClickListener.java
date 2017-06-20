package com.leeiidesu.libmvp.widget.recycler.listener;

import android.view.View;

/**
 * Created by Lee on 2016/12/9.
 */

public interface ItemLongClickListener {
    /**
     * 长按RecyclerView点击监听
     *
     * @param v        布局View
     * @param position 索引角标
     * @return 是否消耗
     */
    boolean onRecyclerItemLongClick(View v, int position);
}
