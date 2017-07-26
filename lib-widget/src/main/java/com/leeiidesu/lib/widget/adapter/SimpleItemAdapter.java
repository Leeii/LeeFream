package com.leeiidesu.lib.widget.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leeiidesu.lib.widget.R;

/**
 * _ SimpleItemAdapter _ Created by dgg on 2017/7/20.
 */

public abstract class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ViewHolder> {

    private int mItemLayout;
    private int mSelectorId;

    public SimpleItemAdapter(@LayoutRes int itemLayoutRes) {
        this(itemLayoutRes, -1);
    }

    public SimpleItemAdapter(@LayoutRes int itemLayoutRes, @DrawableRes int itemSelectorId) {
        this.mItemLayout = itemLayoutRes;
        this.mSelectorId = itemSelectorId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mItemLayout, parent, false);

        return new ViewHolder(itemView, mSelectorId);
    }

    protected class ViewHolder extends
            RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ViewHolder(final View itemView, @DrawableRes int itemSelectorId) {
            super(itemView);
            if (itemSelectorId != -1) {
                itemView.setBackgroundResource(itemSelectorId);
            }

            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }

            if (mOnItemLongClickListener != null) {
                itemView.setOnLongClickListener(this);
            }
        }

        @SuppressWarnings({"unchecked", "UnusedParameters", "ConstantConditions"})
        public <T extends View> T getViewAs(int id) {
            SparseArray<View> map = (SparseArray<View>)
                    itemView.getTag(R.id.simple_holder_items);

            if (map == null) {
                synchronized (ViewHolder.class) {
                    if (map == null) {
                        map = new SparseArray<>();
                        itemView.setTag(R.id.simple_holder_items, map);
                    }
                }
            }

            View view = map.get(id);

            if (view == null) {
                view = itemView.findViewById(id);
                map.put(id, view);
            }

            return (T) view;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            }
            return true;
        }
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}
