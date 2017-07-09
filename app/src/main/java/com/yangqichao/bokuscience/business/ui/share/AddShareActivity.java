package com.yangqichao.bokuscience.business.ui.share;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.FileBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.bokuscience.common.utils.FileUtils;
import com.yangqichao.commonlib.event.EventSubscriber;
import com.yangqichao.commonlib.event.RxBus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RuntimePermissions
public class AddShareActivity extends BaseActivity {


    private static final int FILE_SELECT_CODE = 111;
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
    @BindView(R.id.rl_file)
    RelativeLayout rlFile;
    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.img_next)
    ImageView imgNext;

    private boolean isClickAble = true;

    private FileBean fileBean;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_share;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        StatusBarUtil.setTranslucent(this);
        RxBus.getDefault().toObservable(FileBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new EventSubscriber<FileBean>() {
                    @Override
                    public void onNextDo(FileBean file) {
                        imgNext.setVisibility(View.GONE);
                        submitStaus.setVisibility(View.VISIBLE);
                        llAddFile.setVisibility(View.VISIBLE);
                        tvFileName.setText(file.getName());
                        isClickAble = false;
                        fileBean = file;
                    }
                });
    }

    @OnClick({R.id.img_back, R.id.img_add, R.id.img_cancel, R.id.rl_file})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                String content = etContent.getText().toString();
                if(TextUtils.isEmpty(content)){
                    showToast("请输入分享内容");
                    return;
                }
                Map<String,RequestBody> bodyMap = new HashMap<>();
                bodyMap.put("userId",RequestBody.create(MediaType.parse("multipart/form-data"),APP.getUserId()));
                bodyMap.put("shareContent",RequestBody.create(MediaType.parse("multipart/form-data"),content));

                String h5 = etH5.getText().toString();
                if(!TextUtils.isEmpty(h5)){
                    bodyMap.put("shareUrl",RequestBody.create(MediaType.parse("multipart/form-data"),h5));
                }

                //构造文件
                if(!isClickAble){
                    File file = new File(fileBean.getPath());
                    //file
//                    bodyMap.put("file",RequestBody.create(MediaType.parse("application/otcet-stream"),file));
//                    fileBody = MultipartBody.Part.createFormData("documentUrl",fileBean.getName()
//                            ,RequestBody.create(MediaType.parse("application/otcet-stream"),file))

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("application/otcet-stream"), file));
                    RequestUtil.createApi().insertInfo(bodyMap,body).compose(RequestUtil.<String>handleResult())
                            .subscribe(new CommonsSubscriber<String>() {
                                @Override
                                protected void onSuccess(String s) {
                                    finish();
                                }
                            });
                }else{
                    RequestUtil.createApi().insertInfo(bodyMap).compose(RequestUtil.<String>handleResult())
                            .subscribe(new CommonsSubscriber<String>() {
                                @Override
                                protected void onSuccess(String s) {
                                    finish();
                                }
                            });
                }
                break;
            case R.id.img_cancel:
                imgNext.setVisibility(View.VISIBLE);
                submitStaus.setVisibility(View.GONE);
                llAddFile.setVisibility(View.GONE);
                isClickAble = true;
                break;

            case R.id.rl_file:
                if(isClickAble){
                    startActivity(new Intent(this,FileListActivity.class));
                }
                break;
        }
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void showFileChooser() {
        FileUtils.getSpecificTypeOfFile(this, new String[]{".doc", ".pdf"});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddShareActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
