package com.yangqichao.bokuscience.business.ui.video;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.InitVideoBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoMainActivity extends BaseActivity {
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

    VideoFragment hospitalFragment, sysFragment;
    private Fragment currentFragment;

    private List<InitVideoBean.SubjectListBean> subjectListBean;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_video_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


        RequestUtil.createApi().initVideo(APP.getUserId()).compose(RequestUtil.<InitVideoBean>handleResult())
                .subscribe(new CommonsSubscriber<InitVideoBean>() {
                    @Override
                    protected void onSuccess(InitVideoBean initVideoBean) {
                        subjectListBean = initVideoBean.getSubjectList();
                        swichType(false);
                    }
                });


    }


    @OnClick({R.id.tv_hospital_video, R.id.tv_sys_video, R.id.img_video_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hospital_video:
                swichType(true);
                break;
            case R.id.tv_sys_video:
                swichType(false);
                break;
            case R.id.img_video_search:
                SearchVideoActivity.startAction(this,subjectListBean);
                break;
        }
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
            if(hospitalFragment == null) {
                hospitalFragment = VideoFragment.newInstance(subjectListBean,1);
            }
            currentFragment = hospitalFragment;
        } else {
            viewBttomSys.setVisibility(View.VISIBLE);
            viewBttomHopital.setVisibility(View.GONE);
            tvHospitalVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvSysVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvHospitalVideo.setTextColor(getColorResource(R.color.base_bg_gray));
            tvSysVideo.setTextColor(getColorResource(R.color.white));

            if(sysFragment == null){
                sysFragment = VideoFragment.newInstance(subjectListBean,0);
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



    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


}
