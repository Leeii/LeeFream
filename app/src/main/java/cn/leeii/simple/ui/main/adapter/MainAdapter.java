package cn.leeii.simple.ui.main.adapter;

import android.widget.TextView;

import com.leeiidesu.lib.widget.adapter.SimpleItemAdapter;

import cn.leeii.simple.R;

/**
 * _ MainAdapter _ Created by dgg on 2017/7/20.
 */

public class MainAdapter extends SimpleItemAdapter {

    public MainAdapter() {
        super(R.layout.layout_recycler_not_has_item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView viewAsClass = holder.getViewAsClass(R.id.text, TextView.class);

        String s = viewAsClass.getText().toString();

        viewAsClass.setText(s + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
