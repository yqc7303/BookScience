package com.yangqichao.bokuscience.business.ui.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.VideoListBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoDetailActivity extends BaseActivity {

    VideoListBean.RecordsBean bean;
    @BindView(R.id.tv_video_title)
    TextView tvVideoTitle;
    @BindView(R.id.tv_subject_name)
    TextView tvSubjectName;
    @BindView(R.id.jc_player)
    JCVideoPlayerStandard jcPlayer;
    @BindView(R.id.tv_video_time)
    TextView tvVideoTime;
    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @BindView(R.id.tv_video_content)
    TextView tvVideoContent;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black),0);

        bean = (VideoListBean.RecordsBean) getIntent().getSerializableExtra("bean");
        tvVideoContent.setText(bean.getContent());
        tvVideoTitle.setText(bean.getTitle());
        tvSubjectName.setText(bean.getSubjectName());
        tvVideoTime.setText(bean.getGmtCreate()+"");
        tvHospitalName.setText(bean.getHospitalName());

        jcPlayer.setUp( bean.getVideoUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
    }

    public static void startAction(Context context, VideoListBean.RecordsBean recordsBean) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("bean", recordsBean);
        context.startActivity(intent);
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
