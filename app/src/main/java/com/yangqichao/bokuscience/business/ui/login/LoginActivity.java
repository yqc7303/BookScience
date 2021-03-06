package com.yangqichao.bokuscience.business.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.MainActivity;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.commonlib.util.CheckUtils;
import com.yangqichao.commonlib.util.PreferenceUtils;
import com.yangqichao.commonlib.widget.CleanTextErrorWatcher;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);

        etAccount.addTextChangedListener(new CleanTextErrorWatcher(textInputLayoutAccount,tvError));
        etPassword.addTextChangedListener(new CleanTextErrorWatcher(textInputLayoutPassword,tvError));
    }


    @OnClick({R.id.tv_forget, R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                Intent intent = new Intent(this,RegisterActivity.class);
                intent.putExtra("isForgetPw",true);
                startActivity(intent);
                break;
            case R.id.btn_login:
                final String phone = etAccount.getText().toString();
                final String pw = etPassword.getText().toString();
                if(!CheckUtils.isPhoneValid(phone)){
                    textInputLayoutAccount.setError("手机格式有误");
                    return;
                }
                if(!CheckUtils.isLengthValid(pw,6)){
                    textInputLayoutPassword.setError("密码有误");
                    return;
                }
                RequestBody requestBody = new RequestBody();
                requestBody.setLoginName(phone);
                requestBody.setPassword(pw);
                requestBody.setAppType("0");
                requestBody.setCid(PreferenceUtils.getPrefString(LoginActivity.this,"clientid",""));
                RequestUtil.createApi().login(requestBody).compose(RequestUtil.<LoginBean>handleResult())
                        .subscribe(new CommonsSubscriber<LoginBean>() {
                            @Override
                            protected void onSuccess(LoginBean loginBean) {
                                String mark = PreferenceUtils.getPrefString(LoginActivity.this,"mark","");
                                if(!TextUtils.isEmpty(mark)){
                                    String[] split = mark.split("-");
                                    List<LoginBean.ModuleDTOSBean> beanList = new ArrayList<LoginBean.ModuleDTOSBean>();
                                    for(LoginBean.ModuleDTOSBean dtosBean:loginBean.getModuleDTOS()){
                                        dtosBean.setGone(false);
                                        for(int i = 0;i<split.length;i++){
                                            if(split[i].equals(dtosBean.getCode())){
                                                dtosBean.setGone(true);
                                            }
                                        }
                                    }
                                    for(LoginBean.ModuleDTOSBean dtosBean:loginBean.getModuleDTOS()){
                                        if(!dtosBean.isGone()){
                                            beanList.add(dtosBean);
                                        }
                                    }
                                    if(beanList.size()==0){
                                        loginBean.setModuleDTOSUser(loginBean.getModuleDTOS());
                                    }else{
                                        loginBean.setModuleDTOSUser(beanList);
                                    }

                                }else {
                                    loginBean.setModuleDTOSUser(loginBean.getModuleDTOS());
                                }


                                MainActivity.startAction(LoginActivity.this,loginBean);
                                PreferenceUtils.setPrefString(LoginActivity.this,"uId",loginBean.getId());
                                PreferenceUtils.setPrefString(LoginActivity.this,"pw",pw);
                                PreferenceUtils.setPrefString(LoginActivity.this,"phone",phone);
                                PreferenceUtils.setPrefString(LoginActivity.this,"hospitalId",loginBean.getHospitalId()+"");
                                PreferenceUtils.setPrefString(LoginActivity.this,"hospitalName",loginBean.getHospitalName()+"");
                                PreferenceUtils.setPrefString(LoginActivity.this,"deptId",loginBean.getDeptId()+"");
                                PreferenceUtils.setPrefInt(LoginActivity.this,"publish",loginBean.getPublishFlag());
                                PreferenceUtils.setPrefString(LoginActivity.this,"deptName",loginBean.getDeptName());
                                PreferenceUtils.setPrefString(LoginActivity.this,"provice",loginBean.getProvice());
                                PreferenceUtils.setPrefString(LoginActivity.this,"name",loginBean.getName());
                                PreferenceUtils.setPrefString(LoginActivity.this,"hospitalCode",loginBean.getHospitalCode());
                                PreferenceUtils.setPrefString(LoginActivity.this,"credit",loginBean.getCredit());
                                PreferenceUtils.setPrefInt(LoginActivity.this,"isPwd",loginBean.getIsPwd());
                                finish();
                            }

                            @Override
                            public void onFail(String errorCode, String message) {
                                tvError.setVisibility(View.VISIBLE);
                                tvError.setText(message);
                            }

                            @Override
                            protected void onErrorShow(String errorMsg) {
                            }
                        });

                break;
            case R.id.tv_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
