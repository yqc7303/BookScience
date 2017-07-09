package com.yangqichao.bokuscience.business.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.MainActivity;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LevelBean;
import com.yangqichao.bokuscience.business.bean.RegisteBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterCompleActivity extends BaseActivity {

    @BindView(R.id.tv_keshi)
    TextView tvKeshi;
    @BindView(R.id.tv_hostipal)
    TextView tvHostipal;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    private PopupWindow popupWindow;
    private BaseQuickAdapter<LevelBean, BaseViewHolder> adapter;

    private List<LevelBean> privinceList, hospitalList, keshiList;

    private RecyclerView recyclerViewAddress, recyclerViewHospital, recyclerViewKeshi;

    private LevelBean hospitalBean, keshiBean, addressBean;

    private String phone, code, pw;
    private String brithdayStr;

    public static void startAction(Context context, String phone, String code, String pw) {
        Intent intent = new Intent(context, RegisterCompleActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("code", code);
        intent.putExtra("pw", pw);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_comple;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        pw = getIntent().getStringExtra("pw");
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(pw)) {
            showToast("参数有误");
            finish();
        }


        RequestUtil.createApi().get().compose(RequestUtil.<List<LevelBean>>handleResult()).subscribe(
                new CommonsSubscriber<List<LevelBean>>() {
                    @Override
                    protected void onSuccess(List<LevelBean> provinceBeen) {
                        privinceList = provinceBeen;
                    }
                }
        );


        popupWindow = new PopupWindow(this);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        adapter = new BaseQuickAdapter<LevelBean, BaseViewHolder>(R.layout.item_param) {
            @Override
            protected void convert(BaseViewHolder helper, LevelBean item) {
                helper.setText(R.id.tv_param, item.getOrgName());
            }
        };

        init();


    }

    private void init() {
        recyclerViewAddress = new RecyclerView(this);
        recyclerViewAddress.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAddress.setAdapter(adapter);

        recyclerViewHospital = new RecyclerView(this);
        recyclerViewHospital.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHospital.setAdapter(adapter);

        recyclerViewKeshi = new RecyclerView(this);
        recyclerViewKeshi.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewKeshi.setAdapter(adapter);


        recyclerViewAddress.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                LevelBean levelBean = privinceList.get(position);
                tvAddress.setText(levelBean.getOrgName());
                popupWindow.dismiss();

                addressBean = levelBean;
                //清理下级列表
                hospitalList = null;
                hospitalBean = null;
                keshiBean = null;
                keshiBean = null;
                tvHostipal.setText("请选择");
                tvKeshi.setText("请选择");

                RequestUtil.createApi().getLevel(levelBean.getId()).compose(RequestUtil.<List<LevelBean>>handleResult())
                        .subscribe(new CommonsSubscriber<List<LevelBean>>() {
                            @Override
                            protected void onSuccess(List<LevelBean> levelBeen) {
                                hospitalList = levelBeen;
                            }
                        });
            }
        });

        recyclerViewHospital.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                LevelBean levelBean = hospitalList.get(position);
                tvHostipal.setText(levelBean.getOrgName());
                popupWindow.dismiss();

                hospitalBean = levelBean;
                //清理下级列表
                keshiList = null;
                keshiBean = null;
                tvKeshi.setText("请选择");

                RequestUtil.createApi().getLevel(levelBean.getId()).compose(RequestUtil.<List<LevelBean>>handleResult())
                        .subscribe(new CommonsSubscriber<List<LevelBean>>() {
                            @Override
                            protected void onSuccess(List<LevelBean> levelBeen) {
                                keshiList = levelBeen;
                            }
                        });
            }
        });

        recyclerViewKeshi.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                LevelBean levelBean = keshiList.get(position);
                tvKeshi.setText(levelBean.getOrgName());
                popupWindow.dismiss();

                keshiBean = levelBean;

            }
        });

    }


    @OnClick({R.id.btn_login, R.id.img_back, R.id.tv_keshi, R.id.tv_hostipal, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = etName.getText().toString();
                if (addressBean == null) {
                    showToast("请选择地区");
                    return;
                }
                if (hospitalBean == null) {
                    showToast("请选择医院");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    showToast("姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(brithdayStr)) {
                    showToast("请选择生日");
                    return;
                }

                register();
                break;
            case R.id.img_back:
                finish();
                break;

            case R.id.tv_keshi:
                if (keshiList != null && keshiList.size() != 0) {
                    showPopupWindow(recyclerViewKeshi, keshiList, tvKeshi);
                } else {
                    showToast("没有选项");
                }
                break;
            case R.id.tv_hostipal:
                if (hospitalList != null && hospitalList.size() != 0) {
                    showPopupWindow(recyclerViewHospital, hospitalList, tvHostipal);
                } else {
                    showToast("没有选项");
                }
                break;
            case R.id.tv_address:
                showPopupWindow(recyclerViewAddress, privinceList, tvAddress);
                break;
        }
    }

    private void register() {
//        {
//            "birthday": "2017-06-12T02:02:16.658Z",
//                "checkCode": "string",
//                "deptId": 0,
//                "deptName": "string",
//                "hospitalId": 0,
//                "hospitalName": "string",
//                "name": "string",
//                "tel": "string"
//        }

        RequestBody requestBody = new RequestBody();
        requestBody.setCheckCode(code);
        requestBody.setHospitalId(hospitalBean.getId());
        requestBody.setHospitalName(hospitalBean.getOrgName());
        requestBody.setName(etName.getText().toString());
        requestBody.setTel(phone);
        requestBody.setBirthday(brithdayStr);
        if (keshiBean != null) {
            requestBody.setDeptId(keshiBean.getId());
            requestBody.setDeptName(keshiBean.getOrgName());
        }

        RequestUtil.createApi().registe(requestBody).compose(RequestUtil.<RegisteBean>handleResult())
                .subscribe(new CommonsSubscriber<RegisteBean>() {
                    @Override
                    protected void onSuccess(RegisteBean registeBean) {
                        //// TODO: 2017/6/24 缓存用户信息

                        //

                        startActivity(new Intent(RegisterCompleActivity.this, MainActivity.class));
                    }
                });

    }

    public void showPopupWindow(RecyclerView recyclerView, List<LevelBean> beanList, TextView textView) {

        adapter.setNewData(beanList);

        popupWindow.setContentView(recyclerView);
        popupWindow.showAsDropDown(textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_birthday)
    public void onViewClicked() {
        hideSoftInputView();
        TimePickerView pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                brithdayStr = new SimpleDateFormat("yyyy.MM.dd").format(date);
                tvBirthday.setText(brithdayStr);
            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setSubmitColor(ContextCompat.getColor(this,R.color.base_orange))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this,R.color.base_orange))//取消按钮文字颜色
                .build();
        pickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pickerView.show();
    }
}
