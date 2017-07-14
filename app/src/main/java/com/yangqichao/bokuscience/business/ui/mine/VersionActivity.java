package com.yangqichao.bokuscience.business.ui.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.yangqichao.bokuscience.BuildConfig;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class VersionActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_version;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvVersion.setText(BuildConfig.VERSION_NAME+" (已是最新版本)");
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
