package com.yangqichao.bokuscience.business.ui.video;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.VideoListBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.UrlUtils;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
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

        RequestUtil.createApi().getVideoDetail(getIntent().getIntExtra("position",1)).compose(RequestUtil.<VideoListBean.RecordsBean>handleResult())
                .subscribe(new CommonsSubscriber<VideoListBean.RecordsBean>() {
                    @Override
                    protected void onSuccess(VideoListBean.RecordsBean recordsBean) {
                        dissProgress();
                        bean = recordsBean;
                        tvVideoContent.setText(bean.getContent());
                        tvVideoTitle.setText(bean.getTitle());
                        tvSubjectName.setText(bean.getSubjectName());
                        tvVideoTime.setText(bean.getGmtCreate());
                        tvHospitalName.setText(bean.getHospitalName());

                        jcPlayer.setUp( bean.getVideoUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
                        VideoDetailActivityPermissionsDispatcher.getFirstImgWithCheck(VideoDetailActivity.this);
                    }
                });

    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    public void getFirstImg() {
        Glide.with(this).load(UrlUtils.getpath(bean.getVideoUrl())).into(jcPlayer.thumbImageView);
    }

    public static void startAction(Context context, int position) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        VideoDetailActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
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
