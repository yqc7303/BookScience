package com.yangqichao.bokuscience.business.ui.share;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.FileBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.utils.FileUtils;
import com.yangqichao.commonlib.event.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class FileListActivity extends BaseActivity {

    @BindView(R.id.recycle_file)
    RecyclerView recycleFile;


    private List<FileBean> beanList;


    private BaseQuickAdapter<FileBean,BaseViewHolder> adapter;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_file_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.base_orange));

        adapter = new BaseQuickAdapter<FileBean, BaseViewHolder>(R.layout.item_file) {
            @Override
            protected void convert(BaseViewHolder helper, FileBean item) {
                helper.setText(R.id.tv_file_name,item.getName());
            }
        };
        recycleFile.setLayoutManager(new LinearLayoutManager(this));
        recycleFile.setAdapter(adapter);
        recycleFile.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getDefault().post(beanList.get(position));
                finish();
            }
        });
        adapter.setEmptyView(R.layout.empty_file,recycleFile);
        FileListActivityPermissionsDispatcher.showFileChooserWithCheck(this);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void showFileChooser() {
        beanList = FileUtils.getSpecificTypeOfFile(this, new String[]{
                ".docx",".doc",".pdf",".txt",".xlsx",".xls"
        });
        adapter.setNewData(beanList);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FileListActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
