package cn.leeii.simple.ui.videopicker.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.leeiidesu.lib.common.imageloader.ImageLoader;
import com.leeiidesu.lib.widget.adapter.SimpleItemAdapter;
import com.leeiidesu.libcore.android.UIUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.leeii.simple.R;
import cn.leeii.simple.ui.videopicker.loader.entity.Video;

/**
 * _ VideoAdapter _ Created by dgg on 2017/7/26.
 */

public class VideoAdapter extends SimpleItemAdapter {
    private List<Video> data;
    private SimpleDateFormat mSimpleDateFormat;

    public VideoAdapter(List<Video> data) {
        super(R.layout.layout_video_picker_item);
        this.data = data;

        mSimpleDateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.width = UIUtil.screenPx(holder.itemView.getContext())[0] / 4;
        holder.itemView.setLayoutParams(layoutParams);

        Video video = data.get(position);

        ImageView image = holder.getViewAs(R.id.image);
        String uri = video.getPath();
        ImageLoader.getInstance().display(uri, image);

        String format = mSimpleDateFormat.format(new Date(video.getDuration()));


        TextView duration = holder.getViewAs(R.id.dateTime);
        duration.setText(format);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
