package com.yangqichao.bokuscience.business.ui.meetting;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.MeetingDetailBean;
import com.yangqichao.bokuscience.business.bean.ShowPersonBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MeetingDetailActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener, AMapLocationListener {

    @BindView(R.id.tv_meeting_name)
    TextView tvMeetingName;
    @BindView(R.id.tv_meeting_describe)
    TextView tvMeetingDescribe;
    @BindView(R.id.tv_meeting_host)
    TextView tvMeetingHost;
    @BindView(R.id.tv_meeting_time)
    TextView tvMeetingTime;
    @BindView(R.id.tv_meeting_address)
    TextView tvMeetingAddress;
    @BindView(R.id.tv_meeting_person)
    TextView tvMeetingPerson;
    @BindView(R.id.rl_meeting_file)
    RelativeLayout rlMeetingFile;
    @BindView(R.id.tv_meeting_h5)
    TextView tvMeetingH5;
    @BindView(R.id.tv_meeting_create_time)
    TextView tvMeetingCreateTime;
    @BindView(R.id.rl_normal_close)
    RelativeLayout rlNormalClose;
    @BindView(R.id.rl_normal_open)
    RelativeLayout rlNormalOpen;
    @BindView(R.id.rl_create_close)
    RelativeLayout rlCreateClose;
    @BindView(R.id.rl_create_open)
    RelativeLayout rlCreateOpen;
    @BindView(R.id.switch_sao)
    SwitchCompat switchSao;
    @BindView(R.id.rl_meeting_h5)
    RelativeLayout rlMeetingH5;
    @BindView(R.id.ll_meeting_create)
    LinearLayout llMeetingCreate;
    @BindView(R.id.img_create)
    LinearLayout imgCreate;
    private int meetingId;
    MeetingDetailBean meetingDetail;
    int meetingStatus;

    private static final int REQUESTCODE = 300;
    private String codeUrl;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_meeting_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        meetingId = getIntent().getIntExtra("id", -1);
        meetingStatus = getIntent().getIntExtra("meetingStatus",-1);

        RequestUtil.createApi().meetingDetail(meetingId).compose(RequestUtil.<MeetingDetailBean>handleResult())
                .subscribe(new CommonsSubscriber<MeetingDetailBean>() {
                    @Override
                    protected void onSuccess(MeetingDetailBean meetingDetailBean) {
                        meetingDetail = meetingDetailBean;
                        initPage();
                    }
                });


    }

    private void initPage() {
        tvMeetingName.setText(meetingDetail.getTitle());
        tvMeetingDescribe.setText(meetingDetail.getContent());
        tvMeetingHost.setText(meetingDetail.getCreaterName());
        tvMeetingTime.setText(meetingDetail.getGmtStart());
        tvMeetingAddress.setText(meetingDetail.getAddress());
        tvMeetingCreateTime.setText(String.format(getString(R.string.meeting_create_time), meetingDetail.getGmtCreate()));
        tvMeetingPerson.setText(meetingDetail.getMeetingjoinNum() + "人");
        tvMeetingH5.setText(meetingDetail.getH5Url());
        if (!TextUtils.isEmpty(meetingDetail.getFileUrl())) {
            rlMeetingFile.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(meetingDetail.getH5Url())) {
            rlMeetingH5.setVisibility(View.VISIBLE);
        }

        //处理底部
        boolean signStatus = meetingDetail.getSignflag() == 1;
        String createId = meetingDetail.getCreaterId() + "";
        if (createId.equals(APP.getUserId())) {
            //创建人
            llMeetingCreate.setVisibility(View.VISIBLE);

            setStatusView(signStatus);
            switchSao.setOnCheckedChangeListener(this);
        } else {
            //普通者
            if (signStatus) {
                rlNormalOpen.setVisibility(View.VISIBLE);
            } else {
                rlNormalClose.setVisibility(View.VISIBLE);
            }

        }
        if(meetingDetail.getSignflag()==1){
            switchSao.setChecked(true);
        }else{
            switchSao.setChecked(false);
        }


        // TODO: 2017/7/1 取消会议显示
        if(meetingStatus == 0 && createId.equals(APP.getUserId())){
            imgCreate.setVisibility(View.VISIBLE);
        }else{
            imgCreate.setVisibility(View.GONE);
        }
    }

    private void setStatus(final boolean status) {
        RequestUtil.createApi().signflag(meetingId,status?1:0).compose(RequestUtil.<ShowPersonBean>handleResult())
                .subscribe(new CommonsSubscriber<ShowPersonBean>() {
                    @Override
                    protected void onSuccess(ShowPersonBean showPersonBean) {
                        setStatusView(status);
                    }
                });

    }

    private void setStatusView(boolean status) {
        if (status) {
            switchSao.setChecked(true);
            rlCreateOpen.setVisibility(View.VISIBLE);
            rlCreateClose.setVisibility(View.GONE);
        } else {
            switchSao.setChecked(false);
            rlCreateClose.setVisibility(View.VISIBLE);
            rlCreateOpen.setVisibility(View.GONE);
        }
    }

    public static void startAction(Context context, int meetingId,int meetingStatus) {
        Intent intent = new Intent(context, MeetingDetailActivity.class);
        intent.putExtra("id", meetingId);
        intent.putExtra("meetingStatus", meetingStatus);
        context.startActivity(intent);
    }


    @OnClick({R.id.img_back, R.id.tv_meeting_person, R.id.rl_meeting_file, R.id.tv_meeting_h5, R.id.rl_normal_open, R.id.rl_create_open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_meeting_person:
                MeetingPersonShowActivity.startAction(this,meetingId+"");
                break;
            case R.id.rl_meeting_file:
                openFile(meetingDetail.getFileUrl());
                break;
            case R.id.rl_meeting_h5:
                openFile(meetingDetail.getH5Url());
                break;
            case R.id.rl_normal_open:
                MeetingDetailActivityPermissionsDispatcher.saomaWithCheck(this);
                break;
            case R.id.rl_create_open:
                MeetingDetailActivityPermissionsDispatcher.saomaWithCheck(this);
                break;
            case R.id.img_create:
                View dismissView = LayoutInflater.from(this).inflate(R.layout.popwindow_dismiss_meeting,null);
                ImageView imageView = (ImageView) dismissView.findViewById(R.id.img_cancel);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                final PopupWindow popupWindow = new PopupWindow(dismissView, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setTouchable(true);
                popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1f);
                    }
                });
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
        }
    }



    private void openFile(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(meetingDetail.getFileUrl());
        intent.setData(content_url);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        setStatus(b);
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @NeedsPermission(Manifest.permission.CAMERA)
    public void saoma() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent,REQUESTCODE);
    }
    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void sign() {
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：该方法默认为false。
//        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);


        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                String location = amapLocation.getLongitude() + "," + amapLocation.getLatitude();
                RequestUtil.createApi().sign(codeUrl+"/"+APP.getUserId()+"/"+location).compose(RequestUtil.<String>handleResult())
                        .subscribe(new CommonsSubscriber<String>() {
                            @Override
                            protected void onSuccess(String s) {

                            }
                        });
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MeetingDetailActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient!=null){
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUESTCODE == requestCode && data != null) {
            codeUrl = data.getExtras().getString("result");
            MeetingDetailActivityPermissionsDispatcher.signWithCheck(this);
        }
    }
}
