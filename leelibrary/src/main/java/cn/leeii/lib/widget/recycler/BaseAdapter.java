package cn.leeii.lib.widget.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.leeii.lib.R;
import cn.leeii.lib.utils.UIUtil;
import cn.leeii.lib.widget.ProgressWheel;
import cn.leeii.lib.widget.recycler.listener.ItemClickListener;
import cn.leeii.lib.widget.recycler.listener.ItemLongClickListener;

/**
 * 列表适配器 Created by Leeii on 2015/3/28.
 */
public abstract class BaseAdapter extends
        RecyclerView.Adapter<BaseAdapter.ViewHolder> {

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
    private boolean isLoad;

    /**
     * 常规内部Item
     */
    protected final int TYPE_ITEM = 0;

    /**
     * 底部加载更多Item
     */
    protected final int TYPE_FOOTER = 1;
    /**
     * 头部
     */
    protected final int TYPE_HEADER = 2;
    /**
     * 加载中
     */
    protected final int TYPE_LOADING = 3;
    /**
     * 空布局
     */
    protected final int TYPE_EMPTY = 4;

    /**
     * 状态
     */
    public static final int STATE_LOADING = 0;
    public static final int STATE_CONTENT = 1;
    public static final int STATE_EMPTY = 2;

    //headerView
    private View mHeaderView;
    //当前状态
    private int mCurrentStatus;

    //无数据布局
    private View emptyLayout;


    /**
     * Item点击事件对象
     */
    private ItemClickListener mItemClickListener;

    /**
     * Item长按点击事件对象
     */
    private ItemLongClickListener mItemLongClickListener;
    private int loadMoreBarColor;
    private int loadingBarColor;

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

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return newHolder(mHeaderView, TYPE_HEADER);
            case TYPE_EMPTY:
                if (emptyLayout == null) {
                    emptyLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_recycler_not_has_item, parent, false);
                }
                return newHolder(emptyLayout, TYPE_EMPTY);
            case TYPE_LOADING:
                // 设置颜色
                View loadingLayout = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_load_more,
                        parent, false);
                ProgressWheel loading = (ProgressWheel) loadingLayout.findViewById(R.id.progress);
                int dp64 = UIUtil.dipToPx(mContext, 64);
                LinearLayout.LayoutParams loadingParams = new LinearLayout.LayoutParams(dp64, dp64);
                loading.setLayoutParams(loadingParams);
                loading.setBarColor(loadingBarColor == 0 ? Color.GRAY : loadingBarColor);
                loading.setEnabled(false);
                loadingLayout.setPadding(0, dp64, 0, dp64);
                return newHolder(loadingLayout, TYPE_LOADING);
            case TYPE_ITEM:
                return newHolder(LayoutInflater.from(mContext).inflate(
                        mItemLayout,
                        parent, false),
                        TYPE_ITEM);
            case TYPE_FOOTER:
                // 设置颜色
                View loadMoreLayout = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_load_more,
                        parent, false);
                ProgressWheel progressWheel = (ProgressWheel) loadMoreLayout.findViewById(R.id.progress);
                int d40 = UIUtil.dipToPx(mContext, 40);
                LinearLayout.LayoutParams loadMoreParams = new LinearLayout.LayoutParams(d40, d40);
                progressWheel.setLayoutParams(loadMoreParams);
                progressWheel.setBarColor(loadMoreBarColor == 0 ? Color.GRAY : loadMoreBarColor);
                progressWheel.setEnabled(false);
                return newHolder(loadMoreLayout, TYPE_FOOTER);
        }
        return null;
    }

    public void setLoadMoreBarColor(@ColorInt int loadMoreBarColor) {
        this.loadMoreBarColor = loadMoreBarColor;
    }

    public void setLoadingBarColor(@ColorInt int loadingBarColor) {
        this.loadingBarColor = loadingBarColor;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 判断Item
        if (mCurrentStatus == STATE_CONTENT) {
            if (mHeaderView == null) {
                if (position < getItemCount() - 1 || (!isLoad && position == getItemCount() - 1)) {
                    onBindHolder(holder, position);
                }
            } else {
                if (position > 0 && (position < getItemCount() - 2 || (!isLoad && position <= getItemCount() - 1)) || (isLoad && position == getItemCount() - 2)) {
                    onBindHolder(holder, position - 1);
                }
            }
        }
    }

    /**
     * 获取Item数量
     *
     * @return 返回数量包括底部加载
     */
    @Override
    public int getItemCount() {
        int count;
        if (mCurrentStatus != STATE_CONTENT) count = 1;
        else count = getCount();
        if (mHeaderView != null) count += 1;
        if (isLoad) count += 1;
        return count;
    }

    public void changeDisplayStatus(int state) {
        if (mCurrentStatus == state) return;
        notifyDataSetChanged();
        mCurrentStatus = state;
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
        if (mCurrentStatus != STATE_CONTENT) {
            if (mHeaderView != null) {
                if (position == 0) return TYPE_HEADER;
            }
            if (mCurrentStatus == STATE_EMPTY) {
                return TYPE_EMPTY;
            } else {
                return TYPE_LOADING;
            }
        } else {
            if (mHeaderView == null)
                if (isLoad && position + 1 == getItemCount())
                    return TYPE_FOOTER;
                else
                    return TYPE_ITEM;
            else {
                if (position == 0) return TYPE_HEADER;
                else if (isLoad && position + 1 == getItemCount())
                    return TYPE_FOOTER;
                else
                    return TYPE_ITEM;
            }
        }
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
        public <T extends View> T holderView(int id) {
            return holderView(id, null);
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
        public <T extends View> T holderView(int id, Class<T> clazz) {
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
            mItemClickListener.onRecyclerItemClick(v,
                    mHeaderView == null ? getAdapterPosition() : getAdapterPosition() - 1);
        }

        @Override
        public boolean onLongClick(View v) {
            return mItemLongClickListener.onRecyclerItemLongClick(v,
                    mHeaderView == null ? getAdapterPosition() : getAdapterPosition() - 1);
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

    public interface OnEmptyButtonClickListener {
        void onEmptyButtonClick();
    }

    protected OnEmptyButtonClickListener mOnEmptyButtonClickListener;

    public void setOnEmptyButtonClickListener(OnEmptyButtonClickListener mOnEmptyButtonClickListener) {
        this.mOnEmptyButtonClickListener = mOnEmptyButtonClickListener;
    }
}
