package com.yangqichao.democenter.business.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.folioreader.activity.FolioActivity;
import com.yangqichao.democenter.R;
import com.yangqichao.democenter.common.base.BaseActivity;

import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class EpubReaderActivity extends BaseActivity {


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_epub_reader;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @OnClick({R.id.button_1, R.id.button_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                EpubReaderActivityPermissionsDispatcher.openBookWithCheck(this);
                break;
            case R.id.button_2:

                break;
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void openBook() {
        Intent intent = new Intent(this, FolioActivity.class);
        intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, "149600.epub");
        intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE, FolioActivity.EpubSourceType.ASSESTS);
        startActivity(intent);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRation(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("阅读期刊需要存储权限，请给及")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }


                })
                .setCancelable(false)
                .show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EpubReaderActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
