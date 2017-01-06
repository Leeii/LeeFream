package cn.leeii.lib.widget.recycler.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView滑动加载更多 Created by Leeii on 2015/3/28.
 */
public class RecyclerListener extends RecyclerView.OnScrollListener {

    /**
     * ActionBar隐藏距离
     */
    private static final int HIDE_THRESHOLD = 20;

    /**
     * 滑动距离
     */
    private int mScrolledDistance = 0;

    /**
     * 控制显示
     */
    private boolean mControlsVisible = true;

    /**
     * 最后一个可见Item
     */
    private int lastVisibleItem = 0;

    /**
     * 加载更多的监听
     */
    private LoadMoreListener mListener;

    /**
     * RecyclerView滑动监听构造方法
     *
     * @param listener 加载更多的监听
     */
    public RecyclerListener(LoadMoreListener listener) {
        mListener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mListener != null)
            lastVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

        int firstVisibleItem =
                ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

        if (firstVisibleItem == 0) {
            if (!mControlsVisible) {
                onShow();
                mControlsVisible = true;
            }
        } else {
            if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) {
                onHide();
                mControlsVisible = false;
                mScrolledDistance = 0;
            } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible) {
                onShow();
                mControlsVisible = true;
                mScrolledDistance = 0;
            }
        }
        if ((mControlsVisible && dy > 0) || (!mControlsVisible && dy < 0))
            mScrolledDistance += dy;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (mListener != null && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount())
            mListener.onLoadMore();
    }

    /**
     * 隐藏执行
     */
    public void onHide() {

    }

    /**
     * 显示执行
     */
    public void onShow() {

    }
}
