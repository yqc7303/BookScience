package com.yangqichao.bokuscience.business.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.ui.login.LoginActivity;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.commonlib.util.AppManager;
import com.yangqichao.commonlib.util.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineActivity extends BaseActivity {


    @BindView(R.id.tv_keshi_show)
    TextView tvKeshiShow;
    @BindView(R.id.tv_hostipal)
    TextView tvHostipal;
    @BindView(R.id.tv_keshi)
    TextView tvKeshi;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_xuefen)
    TextView tvXuefen;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);

        tvHostipal.setText(PreferenceUtils.getPrefString(MineActivity.this, "hospitalName", ""));
        tvProvince.setText(PreferenceUtils.getPrefString(MineActivity.this, "provice", ""));
        tvPhone.setText(PreferenceUtils.getPrefString(MineActivity.this, "phone", ""));
        String deptName = PreferenceUtils.getPrefString(MineActivity.this, "deptName", "");
        tvName.setText(PreferenceUtils.getPrefString(MineActivity.this, "name", ""));
        String credit = PreferenceUtils.getPrefString(MineActivity.this, "credit", "");
        tvXuefen.setText(credit.equals("0.0")?"0分":credit+"分");
        if (TextUtils.isEmpty(deptName)) {
            tvKeshiShow.setVisibility(View.GONE);
            tvKeshi.setVisibility(View.GONE);
        } else {
            tvKeshi.setText(deptName);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_change_pw, R.id.tv_feedback, R.id.tv_version, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change_pw:
                startActivity(new Intent(this, ChangePWActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.tv_version:
                startActivity(new Intent(this, VersionActivity.class));
                break;
            case R.id.tv_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage("是否退出登录？")
                        .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AppManager.getAppManager().finishAllActivity();
                                startActivity(new Intent(MineActivity.this, LoginActivity.class));
                                PreferenceUtils.setPrefString(MineActivity.this, "uId", "");
                                PreferenceUtils.setPrefString(MineActivity.this, "pw", "");
                                PreferenceUtils.setPrefString(MineActivity.this, "phone", "");
                                PreferenceUtils.setPrefString(MineActivity.this, "hospitalId", "");
                                PreferenceUtils.setPrefString(MineActivity.this, "hospitalName", "");
                                PreferenceUtils.setPrefString(MineActivity.this, "deptId", "");
                                PreferenceUtils.setPrefInt(MineActivity.this, "publish", 0);
                            }
                        })
                        .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
                break;
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
