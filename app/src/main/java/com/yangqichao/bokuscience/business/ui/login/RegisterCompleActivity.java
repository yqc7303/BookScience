package com.yangqichao.bokuscience.business.ui.login;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LevelBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.util.List;

import butterknife.BindView;
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

    private PopupWindow popupWindow;
    private BaseQuickAdapter<LevelBean, BaseViewHolder> adapter;

    private List<LevelBean> privinceList, hospitalList, keshiList;

    private RecyclerView recyclerViewAddress, recyclerViewHospital, recyclerViewKeshi;

    private LevelBean hospitalBean, keshiBean, addressBean;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_comple;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
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
                if(addressBean==null){
                    showToast("请选择地区");
                    return;
                }
                if(hospitalBean==null){
                    showToast("请选择医院");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    showToast("姓名不能为空");
                    return;
                }
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

    public void showPopupWindow(RecyclerView recyclerView, List<LevelBean> beanList, TextView textView) {

        adapter.setNewData(beanList);

        popupWindow.setContentView(recyclerView);
        popupWindow.showAsDropDown(textView);
    }

}
