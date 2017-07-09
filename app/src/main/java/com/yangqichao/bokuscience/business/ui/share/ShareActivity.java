package com.yangqichao.bokuscience.business.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.ShareItemBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity {


    @BindView(R.id.recycle_share)
    RecyclerView recycleShare;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    BaseQuickAdapter<ShareItemBean.RecordsBean,BaseViewHolder> adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_share;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new BaseQuickAdapter<ShareItemBean.RecordsBean, BaseViewHolder>(R.layout.item_share) {
            @Override
            protected void convert(BaseViewHolder helper, ShareItemBean.RecordsBean item) {
                helper.setText(R.id.tv_user_info,item.getDeptName()+"."+item.getUserName());
                helper.setText(R.id.tv_user_tel,item.getUserTel());
                helper.setText(R.id.tv_share_content,item.getShareContent());
                String documentUrl = item.getDocumentUrl();
                if(!TextUtils.isEmpty(documentUrl)){
                    helper.setVisible(R.id.ly_file,true);
                    helper.setText(R.id.tv_file_content, documentUrl.substring(documentUrl.lastIndexOf("/")+1));
                    // TODO: 2017/7/4 根据后缀名修改图标
                }

                if(!TextUtils.isEmpty(item.getShareUrl())){
                    helper.setVisible(R.id.ly_h5,true);
                    helper.setText(R.id.tv_h5_content, item.getShareTitle());
                    Glide.with(ShareActivity.this).load(item.getShareImg()).into((ImageView) helper.getView(R.id.img_h5_content));
                }
            }
        };

        recycleShare.setLayoutManager(new LinearLayoutManager(this));
        recycleShare.setAdapter(adapter);
        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());
        requestBody.setPage(1);
        requestBody.setPageSize(100);
        RequestUtil.createApi().selectShare(requestBody).compose(RequestUtil.<ShareItemBean>handleResult()).subscribe(
                new CommonsSubscriber<ShareItemBean>() {
                    @Override
                    protected void onSuccess(ShareItemBean shareItemBean) {
                        adapter.setNewData(shareItemBean.getRecords());
                    }
                }
        );
    }


    @OnClick({R.id.img_back, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_add:
                startActivity(new Intent(this,AddShareActivity.class));
                break;
        }
    }
}
