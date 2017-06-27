package com.yangqichao.bokuscience.business.ui.share;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity {


    @BindView(R.id.recycle_share)
    RecyclerView recycleShare;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_share;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.img_back, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:

                break;
        }
    }
}