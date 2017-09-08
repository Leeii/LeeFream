package cn.leeii.simple.ui.videopicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.leeiidesu.lib.widget.adapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.videopicker.adapter.VideoAdapter;
import cn.leeii.simple.ui.videopicker.di.DaggerVideoPickerComponent;
import cn.leeii.simple.ui.videopicker.di.VideoPickerModule;
import cn.leeii.simple.ui.videopicker.loader.MediaStoreHelper;
import cn.leeii.simple.ui.videopicker.loader.entity.PhotoDirectory;
import cn.leeii.simple.ui.videopicker.loader.entity.Video;

/**
 * _ VideoPickerActivity _ Created by dgg on 2017/7/26.
 */
public class VideoPickerActivity extends BaseActivity<VideoPickerPresenter> implements VideoPickerContract.IVideoPickerView, OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    VideoAdapter mAdapter;
    private List<Video> mDataList;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        mRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new VideoAdapter(mDataList = new ArrayList<>());


        MediaStoreHelper.getVideoDirs(this, null, new MediaStoreHelper.VideosResultCallback() {
            @Override
            public void onResultCallback(final List<PhotoDirectory> directories) {
                mDataList.addAll(directories.get(0).getVideos()/*0 : 全部文件*/);
                mAdapter.notifyDataSetChanged();
            }
        });

        mRecycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_video_picker;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        DaggerVideoPickerComponent
                .builder()
                .baseComponent(baseComponent)
                .videoPickerModule(new VideoPickerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Video video = mDataList.get(position);

        Intent intent = new Intent();

        intent.putExtra("videoData", video);
        startActivity(intent);

    }
}
