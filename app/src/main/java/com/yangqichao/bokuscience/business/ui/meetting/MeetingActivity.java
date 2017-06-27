package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity {

    @BindView(R.id.recycle_meetting)
    RecyclerView recycleMeetting;
    @BindView(R.id.swipe_meeting)
    SwipeRefreshLayout swipeMeeting;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.img_add, R.id.img_sao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.img_add:
                Intent intent = new Intent(this,CreateMeetingActivity.class);
                startActivity(intent);
                break;
            case R.id.img_sao:
                break;
        }
    }
}
