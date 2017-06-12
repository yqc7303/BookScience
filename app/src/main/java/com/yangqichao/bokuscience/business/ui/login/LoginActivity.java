package com.yangqichao.bokuscience.business.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangqichao.bokuscience.MainActivity;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.CheckUtils;
import com.yangqichao.commonlib.widget.CleanTextWatcher;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.guideline9)
    Guideline guideline9;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.textInputLayout_account)
    TextInputLayout textInputLayoutAccount;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.textInputLayout_password)
    TextInputLayout textInputLayoutPassword;
    @BindView(R.id.imageView4)
    ImageView imageView4;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etAccount.addTextChangedListener(new CleanTextWatcher(textInputLayoutAccount));
        etPassword.addTextChangedListener(new CleanTextWatcher(textInputLayoutPassword));
    }


    @OnClick({R.id.tv_forget, R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:

                break;
            case R.id.btn_login:
                String phone = etAccount.getText().toString();
                String pw = etPassword.getText().toString();
//                if(!CheckUtils.isPhoneValid(phone)){
//                    textInputLayoutAccount.setError("手机格式有误");
//                    return;
//                }
                if(!CheckUtils.isLengthValid(pw,6)){
                    textInputLayoutPassword.setError("密码有误");
                    return;
                }
                RequestBody requestBody = new RequestBody();
                requestBody.setLoginName(phone);
                requestBody.setPassword(pw);
                RequestUtil.createApi().login(requestBody).compose(RequestUtil.<LoginBean>handleResult())
                        .subscribe(new CommonsSubscriber<LoginBean>() {
                            @Override
                            protected void onSuccess(LoginBean loginBean) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        });

                break;
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
