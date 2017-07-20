package com.yangqichao.bokuscience.business.ui.message;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DynamicListActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_hospital_video)
    TextView tvHospitalVideo;
    @BindView(R.id.tv_sys_video)
    TextView tvSysVideo;
    @BindView(R.id.framelayout_video)
    FrameLayout framelayoutVideo;
    @BindView(R.id.view_bttom_hopital)
    View viewBttomHopital;
    @BindView(R.id.view_bttom_sys)
    View viewBttomSys;


    MessageFragment hospitalFragment, sysFragment;
    private Fragment currentFragment;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_dynamic_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        swichType(true);
    }

    private void swichType(boolean isHospitalVideo) {
        if (hospitalFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(hospitalFragment).commitAllowingStateLoss();
        }
        if (sysFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(sysFragment).commitAllowingStateLoss();
        }
        if (isHospitalVideo) {
            viewBttomSys.setVisibility(View.GONE);
            viewBttomHopital.setVisibility(View.VISIBLE);
            tvHospitalVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvSysVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvHospitalVideo.setTextColor(getColorResource(R.color.white));
            tvSysVideo.setTextColor(getColorResource(R.color.base_bg_gray));
            if (hospitalFragment == null) {
                hospitalFragment = MessageFragment.newInstance(true);
            }
            currentFragment = hospitalFragment;
        } else {
            viewBttomSys.setVisibility(View.VISIBLE);
            viewBttomHopital.setVisibility(View.GONE);
            tvHospitalVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvSysVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvHospitalVideo.setTextColor(getColorResource(R.color.base_bg_gray));
            tvSysVideo.setTextColor(getColorResource(R.color.white));

            if (sysFragment == null) {
                sysFragment = MessageFragment.newInstance(false);
            }
            currentFragment = sysFragment;
        }

        if (currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout_video, currentFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.tv_hospital_video, R.id.tv_sys_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_hospital_video:
                swichType(true);
                break;
            case R.id.tv_sys_video:
                swichType(false);
                break;
        }
    }
}
