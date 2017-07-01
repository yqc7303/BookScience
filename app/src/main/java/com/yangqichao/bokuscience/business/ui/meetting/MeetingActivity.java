package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.MyMeetingBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MeetingActivity extends BaseActivity {

    @BindView(R.id.recycle_meetting)
    RecyclerView recycleMeetting;
    @BindView(R.id.swipe_meeting)
    SwipeRefreshLayout swipeMeeting;
    @BindView(R.id.img_add)
    ImageView imgAdd;

    private BaseQuickAdapter<MyMeetingBean.RecordsBean,BaseViewHolder> adapter;
    private List<MyMeetingBean.RecordsBean> records;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        int publish = PreferenceUtils.getPrefInt(this, "publish", -1);
        if (publish == 1) {
            imgAdd.setVisibility(View.VISIBLE);
        }

        adapter = new BaseQuickAdapter<MyMeetingBean.RecordsBean, BaseViewHolder>(R.layout.item_my_meeting) {
            @Override
            protected void convert(BaseViewHolder helper, MyMeetingBean.RecordsBean item) {
                // TODO: 2017/7/1 会议状态样式

                helper.setText(R.id.tv_meeting_title,item.getMeetingTitle());

                String[] time = item.getGmtCreate().split(" ");
                helper.setText(R.id.tv_meeting_date,time[0]);
                helper.setText(R.id.tv_meeting_time,time[1]);
                // TODO: 2017/7/1 会议状态
//                helper.setImageResource(R.id.img_meeting_status,R.drawable.icon_meet_1);
            }
        };
        recycleMeetting.setLayoutManager(new LinearLayoutManager(this));
        recycleMeetting.setAdapter(adapter);
        recycleMeetting.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyMeetingBean.RecordsBean recordsBean = records.get(position);
                MeetingDetailActivity.startAction(MeetingActivity.this, recordsBean.getMeetingId(),recordsBean.getMeetingState());
            }
        });


        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());
        RequestUtil.createApi().select(requestBody).compose(RequestUtil.<MyMeetingBean>handleResult())
                .subscribe(new CommonsSubscriber<MyMeetingBean>() {
                    @Override
                    protected void onSuccess(MyMeetingBean myMeetingBean) {
                        records = myMeetingBean.getRecords();
                        adapter.setNewData(records);
                    }
                });
    }


    @OnClick({R.id.img_back, R.id.img_add, R.id.img_sao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                Intent intent = new Intent(this, CreateMeetingActivity.class);
                startActivity(intent);
                break;
            case R.id.img_sao:
                break;
        }
    }


}
