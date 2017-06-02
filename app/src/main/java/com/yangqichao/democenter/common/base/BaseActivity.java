package com.yangqichao.democenter.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.yangqichao.commonlib.util.AppManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(isFullscreen){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        AppManager.getAppManager().addActivity(this);
        setContentView(getLayoutResID());
        setStatusBar();
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }


    /**
     * 设置全屏
     */
    public void setFullscreen(){
        isFullscreen = true;
    }

    protected abstract int getLayoutResID();

    protected abstract void initView(Bundle savedInstanceState);

    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 屏幕背景变暗
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = bgAlpha;// 0.0 - 1.0
        getWindow().setAttributes(layoutParams);
    }

    public void showToast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }
    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.colorPrimary),0);
    }


}
