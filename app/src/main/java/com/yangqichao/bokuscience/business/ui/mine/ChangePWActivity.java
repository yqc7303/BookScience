package com.yangqichao.bokuscience.business.ui.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.PreferenceUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePWActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_old_pw)
    EditText etOldPw;
    @BindView(R.id.et_new_pw)
    EditText etNewPw;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_change_pw;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.img_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_submit:
                String phone = etPhone.getText().toString();
                String oldPw = etOldPw.getText().toString();
                final String newPw = etNewPw.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号不为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号不为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    showToast("手机号不为空");
                    return;
                }
                RequestBody requestBody = new RequestBody();
                requestBody.setAppType("0");
                requestBody.setCid(PreferenceUtils.getPrefString(this, "clientid", ""));
                requestBody.setLoginName(PreferenceUtils.getPrefString(this, "name", ""));
                requestBody.setNewPassWord(newPw);
                requestBody.setOldPassWord(oldPw);
                requestBody.setPassword(newPw);
                requestBody.setTel(PreferenceUtils.getPrefString(this, "phone", ""));
                RequestUtil.createApi().motifyPassword(requestBody).compose(RequestUtil.<String>handleResult())
                        .subscribe(new CommonsSubscriber<String>() {
                            @Override
                            protected void onSuccess(String s) {
                                showToast("修改密码成功");
                                PreferenceUtils.setPrefString(ChangePWActivity.this, "pw", newPw);
                                finish();
                            }
                        });

                break;
        }
    }
}
