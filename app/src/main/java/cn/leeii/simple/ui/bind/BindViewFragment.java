package cn.leeii.simple.ui.bind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;

import butterknife.BindView;
import butterknife.OnClick;
import cn.leeii.libmvp.utils.LogUtil;
import cn.leeii.libmvp.utils.PreferenceHelper;
import cn.leeii.simple.R;
import cn.leeii.simple.R2;
import cn.leeii.simple.base.BaseFragment;
import cn.leeii.simple.ui.topic.TopicActivity;

/**
 * Created by Lee on 2017/1/3.
 */

public class BindViewFragment extends BaseFragment<BindViewActivity, BindPresenter> implements BinderContract.IBinderView {
    @BindView(R2.id.button)
    Button button;
    @BindView(R2.id.button2)
    Button button2;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        LogUtil.e("Fragment", "执行了");
        button.setText("哈哈哈");
        button2.setText("哈哈哈2");
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_bind;
    }

    @Override
    public void loadend(String message) {
        PreferenceHelper.commitString("Token", JSON.parseObject(message).getString("Token"));
    }


    @OnClick({R2.id.button, R2.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R2.id.button:
                startActivity(TopicActivity.class,false);
                break;
            case R2.id.button2:
                mPresenter.login("18328431827", "123456");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
