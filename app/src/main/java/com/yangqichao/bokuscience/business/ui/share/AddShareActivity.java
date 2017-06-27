package com.yangqichao.bokuscience.business.ui.share;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShareActivity extends BaseActivity {


    @BindView(R.id.et_h5)
    EditText etH5;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.submit_staus)
    TextView submitStaus;
    @BindView(R.id.tv_file_name)
    TextView tvFileName;
    @BindView(R.id.ll_add_file)
    LinearLayout llAddFile;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_share;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.img_add, R.id.img_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                break;
            case R.id.img_cancel:
                break;
        }
    }
}
