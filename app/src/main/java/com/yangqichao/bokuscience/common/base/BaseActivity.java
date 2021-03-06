package com.yangqichao.bokuscience.common.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.R;
import com.yangqichao.commonlib.util.AppManager;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isFullscreen;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(isFullscreen){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        AppManager.getAppManager().addActivity(this);
        setContentView(getLayoutResID());
//        setStatusBar();
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.base_orange),0);
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
//    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.base_orange),255);
//    }

    public int getColorResource(int color){
        return ContextCompat.getColor(this,color);
    }

    public void showProgress() {
        showToast("xinashi");
        if(dialog == null){
            dialog = new Dialog(this, R.style.full_screen_dialog);
            dialog.setContentView(R.layout.progress_dialog);
            ((TextView) dialog.findViewById(R.id.label_loading)).setText("请稍后");
            dialog.setCancelable(false);
            dialog.show();
        }else{
            dialog.show();
        }
    }
    public void dissProgress() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

}
