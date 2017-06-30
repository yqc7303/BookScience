package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.ui.LocationActivity;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.commonlib.event.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateMeetingActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_meeting_name)
    EditText etMeetingName;
    @BindView(R.id.tv_meeting_describe)
    EditText etMeetingDescribe;
    @BindView(R.id.tv_meeting_time)
    TextView tvMeetingTime;
    @BindView(R.id.tv_meeting_address)
    TextView tvMeetingAddress;
    @BindView(R.id.tv_meeting_person)
    TextView tvMeetingPerson;
    @BindView(R.id.tv_meeting_file)
    TextView tvMeetingFile;
    @BindView(R.id.tv_meeting_xcc)
    EditText tvMeetingXcc;
    @BindView(R.id.switch_duanxin)
    SwitchCompat switchDuanxin;
    @BindView(R.id.tv_create)
    TextView tvCreate;

    private Tip address;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_creat_meeting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.getDefault().toObservable(Tip.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommonsSubscriber<Tip>() {
                    @Override
                    protected void onSuccess(Tip tip) {
                        tvMeetingAddress.setText(tip.getName());
                        address = tip;
                    }
                });

    }


    @OnClick({R.id.tv_meeting_time, R.id.tv_meeting_address, R.id.tv_meeting_person, R.id.tv_meeting_file, R.id.tv_meeting_xcc, R.id.switch_duanxin,R.id.tv_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_meeting_time:
                break;
            case R.id.tv_meeting_address:
                startActivity(new Intent(this, GetLocationActivity.class));
                break;
            case R.id.tv_meeting_person:
                break;
            case R.id.tv_meeting_file:
                break;
            case R.id.tv_meeting_xcc:
                break;
            case R.id.switch_duanxin:
                break;
            case R.id.tv_create:
                
                break;
        }
    }

}
