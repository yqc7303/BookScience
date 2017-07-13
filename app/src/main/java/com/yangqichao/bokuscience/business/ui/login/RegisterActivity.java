package com.yangqichao.bokuscience.business.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.CheckUtils;
import com.yangqichao.commonlib.widget.CleanTextWatcher;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yangqichao.commonlib.util.CheckUtils.isPhoneValid;

public class RegisterActivity extends BaseActivity{

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.textInputLayout_password)
    TextInputLayout textInputLayoutPassword;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.textInputLayout_account)
    TextInputLayout textInputLayoutAccount;
    @BindView(R.id.et_verification)
    EditText etVerification;
    @BindView(R.id.textInputLayout_verification)
    TextInputLayout textInputLayoutVerification;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;

    private MyCountTimer timer;

    private boolean isForgetPw;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        timer = new MyCountTimer(60000,1000);

        etVerification.addTextChangedListener(new CleanTextWatcher(textInputLayoutVerification));
        etPassword.addTextChangedListener(new CleanTextWatcher(textInputLayoutPassword));
        etAccount.addTextChangedListener(new CleanTextWatcher(textInputLayoutAccount));

        isForgetPw = getIntent().getBooleanExtra("isForgetPw",false);
        if(isForgetPw){
//            textInputLayoutPassword.setHint("请设置新密码");
            textInputLayoutPassword.setVisibility(View.GONE);
            btnLogin.setText("找回密码");
        }
    }



    @OnClick({R.id.btn_login, R.id.tv_getcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String tel2 = etAccount.getText().toString();
                String code = etVerification.getText().toString();
                String pw = etPassword.getText().toString();
                if(!CheckUtils.isPhoneValid(tel2)){
                    textInputLayoutAccount.setError("手机号有误");
                    return;
                }
                if(!CheckUtils.isLengthValid(code,6)){
                    textInputLayoutVerification.setError("验证码有误");
                    return;
                }
                if(!CheckUtils.isLengthValid(pw,6)&&!isForgetPw){
                    textInputLayoutPassword.setError("密码不能小于6位");
                    return;
                }
                if(isForgetPw){
                    RequestUtil.createApi().resetpassword(tel2,code).compose(RequestUtil.<String>handleResult())
                            .subscribe(new CommonsSubscriber<String>() {
                                @Override
                                protected void onSuccess(String s) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                                            .setMessage("验证码已通过短信发送")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override





                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                                    finish();
                                                }
                                            }).setCancelable(false);
                                    builder.show();
                                }
                            });
                }else{
                    RegisterCompleActivity.startAction(this,tel2,code,pw);
                    finish();
                }

                break;
            case R.id.tv_getcode:
                String tel = etAccount.getText().toString();
                if(!isPhoneValid(tel)){
                    textInputLayoutAccount.setError("手机号有误");
                    return;
                }
                timer.start();
                tvGetcode.setClickable(false);
                tvGetcode.setBackgroundColor(ContextCompat.getColor(this,R.color.transparent));
                getCode(tel);
                break;
        }
    }


    private void getCode(String tel) {


        RequestUtil.createApi().check(tel).compose(RequestUtil.<String>handleResult())
                .subscribe(new CommonsSubscriber<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        showToast("验证码已发送");
                    }
                });

    }


    class MyCountTimer extends CountDownTimer{

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            tvGetcode.setText(String.format(getString(R.string.code_wait),l/1000));
        }

        @Override
        public void onFinish() {
            tvGetcode.setClickable(true);
            tvGetcode.setBackgroundResource(R.drawable.corner_white);
            tvGetcode.setText("发送验证码");
        }
    }
}
