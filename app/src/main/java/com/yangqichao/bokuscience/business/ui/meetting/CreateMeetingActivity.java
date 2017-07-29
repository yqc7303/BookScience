package com.yangqichao.bokuscience.business.ui.meetting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.FileBean;
import com.yangqichao.bokuscience.business.bean.GetKeShiPerson;
import com.yangqichao.bokuscience.business.ui.share.FileListActivity;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.event.EventSubscriber;
import com.yangqichao.commonlib.event.RxBus;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateMeetingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {


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
    @BindView(R.id.tv_meeting_address_detail)
    EditText tvMeetingAddressDetail;
    @BindView(R.id.tv_meeting_xuefeng)
    TextView tvMeetingXuefeng;
    @BindView(R.id.huiyididian)
    TextView huiyididian;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.tv_duanxin)
    TextView tvDuanxin;
    @BindView(R.id.tv_file_type)
    TextView tvFileType;
    @BindView(R.id.tv_file_content)
    TextView tvFileContent;
    @BindView(R.id.img_file_delete)
    ImageView imgFileDelete;
    @BindView(R.id.ll_file_show)
    LinearLayout llFileShow;

    private PoiItem address;

    private List<GetKeShiPerson.RecordsBean> chooseList;
    private FileBean fileBean;
    private SimpleDateFormat dateFormat;
    private Date meetingDate;
    private List<Double> xuefen;
    private OptionsPickerView pvCustomOptions;
    private String xuefengStr;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_creat_meeting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.getDefault().toObservable(PoiItem.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommonsSubscriber<PoiItem>() {
                    @Override
                    protected void onSuccess(PoiItem poiItem) {
                        String str = poiItem.getProvinceName() + "-" + poiItem.getCityName() + "-"
                                + poiItem.getAdName() + "," + poiItem.getTitle();
                        tvMeetingAddress.setText(str);
                        address = poiItem;

                    }
                });

        RxBus.getDefault().toObservable(FileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new EventSubscriber<FileBean>() {
                    @Override
                    public void onNextDo(FileBean file) {
                        fileBean = file;
                        tvFileType.setText(file.getName().substring(file.getName().lastIndexOf(".")+1).toUpperCase());
                        tvFileContent.setText(file.getName());
                        tvMeetingFile.setVisibility(View.GONE);
                        llFileShow.setVisibility(View.VISIBLE);
//                        tvMeetingFile.setText(file.getName());
                    }
                });
        switchDuanxin.setOnCheckedChangeListener(this);
        dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        xuefen = getXuefen();
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                xuefengStr = xuefen.get(options1) + "";
                tvMeetingXuefeng.setText(xuefengStr);
            }
        })
                .setSubmitColor(ContextCompat.getColor(this, R.color.base_orange))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.base_orange))//取消按钮文字颜色
                .build();
        pvCustomOptions.setPicker(xuefen);
    }


    @OnClick({R.id.tv_meeting_time, R.id.tv_meeting_address, R.id.tv_meeting_person, R.id.img_back,
            R.id.tv_meeting_file, R.id.tv_meeting_xcc, R.id.switch_duanxin, R.id.tv_create, R.id.img_file_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_meeting_time:
                hideSoftInputView();
                TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvMeetingTime.setText(dateFormat.format(date));
                        meetingDate = date;
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                        .setSubmitColor(ContextCompat.getColor(this, R.color.base_orange))//确定按钮文字颜色
                        .setCancelColor(ContextCompat.getColor(this, R.color.base_orange))//取消按钮文字颜色
                        .build();
                pickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pickerView.show();
                break;
            case R.id.tv_meeting_address:
                startActivity(new Intent(this, GetLocationActivity.class));
                break;
            case R.id.tv_meeting_person:
                Intent intent = new Intent(CreateMeetingActivity.this, MeetingPersonActivity.class);
                startActivityForResult(intent, 100);
                if(chooseList!=null){
                    chooseList.clear();
                }
                break;
            case R.id.tv_meeting_file:
                startActivity(new Intent(this, FileListActivity.class));
                break;
            case R.id.tv_create:
                createMeeting();
                break;
            case R.id.img_file_delete:
                fileBean = null;
                tvMeetingFile.setVisibility(View.VISIBLE);
                llFileShow.setVisibility(View.GONE);
                break;
        }
    }

    private void createMeeting() {
        String title = etMeetingName.getText().toString();
        String describe = etMeetingDescribe.getText().toString();
        String time = tvMeetingTime.getText().toString();
        String addressStr = tvMeetingAddress.getText().toString();
        String person = tvMeetingPerson.getText().toString();
        final String h5 = tvMeetingXcc.getText().toString();
        String addressDetail = tvMeetingAddressDetail.getText().toString();

        if (TextUtils.isEmpty(title)) {
            showToast("会议标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(describe)) {
            showToast("会议描述不能为空");
            return;
        }

        if (TextUtils.isEmpty(time)) {
            showToast("会议时间不能为空");
            return;
        }
        if (TextUtils.isEmpty(addressStr)) {
            showToast("会议地点不能为空");
            return;
        }
        if (TextUtils.isEmpty(person)) {
            showToast("请选择参会人员");
            return;
        }
        if (TextUtils.isEmpty(addressDetail)) {
            showToast("会议详细地点不能为空");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (GetKeShiPerson.RecordsBean bean : chooseList) {
            if (bean == chooseList.get(chooseList.size() - 1)) {
                builder.append(bean.getId());
            } else {
                builder.append(bean.getId() + ",");
            }

        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Map<String, RequestBody> bodyMap = new HashMap<>();
        LatLonPoint point = this.address.getLatLonPoint();
        bodyMap.put("title", RequestBody.create(MediaType.parse("multipart/form-data"), title));
        bodyMap.put("content", RequestBody.create(MediaType.parse("multipart/form-data"), describe));
        bodyMap.put("gmtStart", RequestBody.create(MediaType.parse("multipart/form-data"), dateFormat.format(meetingDate)));
        bodyMap.put("address", RequestBody.create(MediaType.parse("multipart/form-data"), addressStr));
        bodyMap.put("gps", RequestBody.create(MediaType.parse("multipart/form-data"), point.getLongitude() + "," + point.getLatitude()));
        bodyMap.put("noticeflag", RequestBody.create(MediaType.parse("multipart/form-data"), switchDuanxin.isChecked() ? "1" : "0"));
        bodyMap.put("createrId", RequestBody.create(MediaType.parse("multipart/form-data"), APP.getUserId()));
        bodyMap.put("hospitalId", RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceUtils.getPrefString(this, "hospitalId", "")));
        bodyMap.put("hospitalName", RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceUtils.getPrefString(this, "hospitalName", "")));
        bodyMap.put("userIds", RequestBody.create(MediaType.parse("multipart/form-data"), builder.toString()));
        bodyMap.put("floor", RequestBody.create(MediaType.parse("multipart/form-data"), addressDetail));
        bodyMap.put("credit", RequestBody.create(MediaType.parse("multipart/form-data"), TextUtils.isEmpty(xuefengStr)?"0.0":xuefengStr));


        if (!TextUtils.isEmpty(h5)) {
            bodyMap.put("h5Url", RequestBody.create(MediaType.parse("multipart/form-data"), h5));
        }


        AlertDialog.Builder builderDialog = new AlertDialog.Builder(this);
        builderDialog.setTitle("会议即将发布")
                .setMessage("会议发布后无法修改，是否继续？")
                .setPositiveButton("继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (fileBean != null) {
                            File file = new File(fileBean.getPath());
                            MultipartBody.Part body =
                                    MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("application/otcet-stream"), file));
                            RequestUtil.createApi().publish(bodyMap, body).compose(RequestUtil.<String>handleResult())
                                    .subscribe(new CommonsSubscriber<String>() {
                                        @Override
                                        protected void onSuccess(String s) {
                                            finish();
                                        }
                                    });
                        } else {
                            RequestUtil.createApi().publish(bodyMap).compose(RequestUtil.<String>handleResult())
                                    .subscribe(new CommonsSubscriber<String>() {
                                        @Override
                                        protected void onSuccess(String s) {
                                            finish();
                                        }
                                    });
                        }
                    }
                })
                .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builderDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            showToast("短信通知已打开");
        } else {
            showToast("短信通知已关闭");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            chooseList = (List<GetKeShiPerson.RecordsBean>) data.getSerializableExtra("choose");
            tvMeetingPerson.setText(chooseList.size() + "人");

        }
    }

    @OnClick(R.id.tv_meeting_xuefeng)
    public void onViewClicked() {
        hideSoftInputView();
        pvCustomOptions.show();
    }

    private List<Double> getXuefen() {
        List<Double> list = new ArrayList<>();
        list.add(0.0);
        for (int i = 1; i <= 20; i++) {
            list.add(i * 0.5);
        }
        return list;
    }




}
