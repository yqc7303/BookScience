package com.yangqichao.bokuscience.business.ui.book;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.InitBookBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class BookMainActivity extends BaseActivity {


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

    private JournalFragment journalFragment;
    private BooKFragment booKFragment;
    private Fragment currentFragment;

    private List<InitBookBean.SubjectsBean> subjectListBean;
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_book_main;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        RequestUtil.createApi().initBook().compose(RequestUtil.<InitBookBean>handleResult())
                .subscribe(new CommonsSubscriber<InitBookBean>() {
                    @Override
                    protected void onSuccess(InitBookBean initBookBean) {
                        subjectListBean = initBookBean.getSubjects();
                        BookMainActivityPermissionsDispatcher.swichTypeWithCheck(BookMainActivity.this,true);
                    }
                });

    }


    @OnClick({R.id.tv_hospital_video, R.id.tv_sys_video, R.id.img_video_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hospital_video:
                BookMainActivityPermissionsDispatcher.swichTypeWithCheck(this,true);
                break;
            case R.id.tv_sys_video:
                BookMainActivityPermissionsDispatcher.swichTypeWithCheck(this,false);
                break;
            case R.id.img_video_search:
                startActivity(new Intent(this, MyBookMainActivity.class));
                break;
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    public void swichType(boolean isJournal) {
        if (journalFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(journalFragment).commitAllowingStateLoss();
        }
        if (booKFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(booKFragment).commitAllowingStateLoss();
        }
        if (isJournal) {
            viewBttomSys.setVisibility(View.GONE);
            viewBttomHopital.setVisibility(View.VISIBLE);
            tvHospitalVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvSysVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvHospitalVideo.setTextColor(getColorResource(R.color.white));
            tvSysVideo.setTextColor(getColorResource(R.color.base_bg_gray));
            if(journalFragment == null) {
                journalFragment = JournalFragment.newInstance(false,subjectListBean);
            }
            currentFragment = journalFragment;
        } else {
            viewBttomSys.setVisibility(View.VISIBLE);
            viewBttomHopital.setVisibility(View.GONE);
            tvHospitalVideo.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tvSysVideo.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvHospitalVideo.setTextColor(getColorResource(R.color.base_bg_gray));
            tvSysVideo.setTextColor(getColorResource(R.color.white));

            if(booKFragment == null){
               booKFragment = BooKFragment.newInstance(false,subjectListBean);
            }
            currentFragment = booKFragment;
        }

        if (currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout_video, currentFragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BookMainActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
