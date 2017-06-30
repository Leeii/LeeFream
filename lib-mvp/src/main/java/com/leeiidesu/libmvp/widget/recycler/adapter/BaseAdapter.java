package com.leeiidesu.libmvp.widget.recycler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leeiidesu.libmvp.widget.ProgressWheel;
import com.leeiidesu.libmvp.widget.recycler.listener.ItemClickListener;
import com.leeiidesu.libmvp.widget.recycler.listener.ItemLongClickListener;

import cn.leeii.libmvp.R;

/**
 * Created by leeiidesu on 2017/6/30.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 布局文件
     */
    protected int mItemLayout;
    /**
     * 是否加载更多
     */
    protected boolean isLoad;
    /**
     * 常规内部Item
     */
    protected final int TYPE_ITEM = 0;
    /**
     * 底部加载更多Item
     */
    protected final int TYPE_FOOTER = 1;
    /**
     * Item点击事件对象
     */
    private ItemClickListener mItemClickListener;
    /**
     * Item长按点击事件对象
     */
    private ItemLongClickListener mItemLongClickListener;

    /**
     * 父类构造
     *
     * @param context    上下文
     * @param itemLayout 布局文件
     */
    public BaseAdapter(Context context, int itemLayout) {
        this(context, itemLayout, false);
    }

    /**
     * 父类构造
     *
     * @param context    上下文
     * @param itemLayout 布局文件
     * @param isLoad     是否加载更多 true 加载更多 false 反之
     */
    public BaseAdapter(Context context, int itemLayout, boolean isLoad) {
        mContext = context;
        mItemLayout = itemLayout;
        this.isLoad = isLoad;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                return newHolder(LayoutInflater.from(mContext).inflate(
                        mItemLayout,
                        parent, false),
                        TYPE_ITEM);
            case TYPE_FOOTER:
                View loadMore = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_load_more,
                        parent, false);
                // 设置颜色
                ProgressWheel progressWheel =
                        (ProgressWheel) loadMore.findViewById(R.id.progress);
                progressWheel.setBarColor(mContext.getResources()
                        .getColor(R.color.progress_color));
                LinearLayout.LayoutParams lp =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                loadMore.setLayoutParams(lp);
                loadMore.setEnabled(false);
                return newHolder(loadMore, TYPE_FOOTER);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 判断Item
        if (position < getItemCount() - 1
                || (!isLoad && position == getItemCount() - 1))
            onBindHolder(holder, position);
    }

    /**
     * 获取Item数量
     *
     * @return 返回数量包括底部加载
     */
    @Override
    public int getItemCount() {
        if (isLoad)
            return getCount() + 1;
        else
            return getCount();
    }

    /**
     * 真实的数据数量
     *
     * @return 数量个数
     */
    protected abstract int getCount();

    /**
     * 绑定Holder数据
     *
     * @param holder   容器
     * @param position 角标
     */
    protected abstract void onBindHolder(ViewHolder holder, int position);

    /**
     * 返回Item属性
     *
     * @param position 角标
     * @return 属性
     */
    @Override
    public int getItemViewType(int position) {
        if (isLoad && position + 1 == getItemCount())
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    /**
     * 返回当前加载状态
     *
     * @return true 加载 false 不加载
     */
    public boolean isLoad() {
        return isLoad;
    }

    /**
     * 停止加载更多
     */
    public void loadFinish() {
        this.isLoad = false;
        notifyItemRemoved(getItemCount() + 1);
    }

    /**
     * 设置是否加载更多
     *
     * @param isLoad 是否加载更多
     */
    public void setLoad(boolean isLoad) {
        this.isLoad = isLoad;
    }

    /**
     * 获取ViewHolder
     *
     * @param itemView     布局View
     * @param itemViewType 类型
     * @return Holder
     */
    public abstract ViewHolder newHolder(View itemView, int itemViewType);

    /**
     * ViewHolder做控件缓存
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnLongClickListener {
        /**
         * 布局View
         */
        private View itemView;

        /**
         * 构造参数
         *
         * @param itemView 布局View
         */
        public ViewHolder(View itemView) {
            this(itemView, -1);
        }

        /**
         * 初始化ViewHolder
         *
         * @param itemView   布局View
         * @param selectorId 点击选择器
         */
        public ViewHolder(View itemView, int selectorId) {
            this(itemView, -1, selectorId);
        }

        /**
         * 初始化ViewHolder
         *
         * @param itemView   布局View
         * @param selectorId 点击选择器
         */
        public ViewHolder(View itemView, int viewId, int selectorId) {
            super(itemView);
            this.itemView = itemView;
            if (viewId != -1)
                this.itemView.setId(viewId);
            if (selectorId != -1)
                itemView.setBackgroundResource(selectorId);
            else
                itemView.setBackgroundResource(R.drawable.trans);
            // 赋值点击事件
            if (mItemClickListener != null)
                itemView.setOnClickListener(this);
            if (mItemLongClickListener != null)
                itemView.setOnLongClickListener(this);
        }

        /**
         * 静态Holder容器
         *
         * @param id  资源Id
         * @param <T> 泛型
         * @return 静态View
         */
        @SuppressWarnings("unchecked")
        public <T extends View> T findView(int id) {
            return findView(id, null);
        }

        /**
         * 静态Holder容器
         *
         * @param id    资源Id
         * @param <T>   泛型
         * @param clazz 传入强转类型
         * @return 静态View
         */
        @SuppressWarnings({"unchecked", "UnusedParameters"})
        public <T extends View> T findView(int id, Class<T> clazz) {
            // 创建容器
            SparseArray<View> holder = (SparseArray<View>) itemView.getTag();
            // 如果容器为空
            if (holder == null) {
                holder = new SparseArray<>();
                itemView.setTag(holder);
            }
            // 容器不为空
            View child = holder.get(id);
            if (child == null) {
                // 获取布局控件
                child = itemView.findViewById(id);
                // 放入容器
                holder.put(id, child);
            }
            // 返回控件TIME_RUN
            return (T) child;
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onRecyclerItemClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return mItemLongClickListener.onRecyclerItemLongClick(v,
                    getAdapterPosition());
        }
    }

    /**
     * 设置Item点击监听
     *
     * @param itemClickListener item点击监听
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    /**
     * 设置Item长按监听
     *
     * @param itemLongClickListener item点击监听
     */
    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }
}
