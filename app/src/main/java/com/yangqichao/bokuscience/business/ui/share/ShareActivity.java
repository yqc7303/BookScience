package com.yangqichao.bokuscience.business.ui.share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.yangqichao.bokuscience.business.ui.CommonWebViewActivity;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{


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
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeResources(R.color.base_orange);



        adapter = new BaseQuickAdapter<ShareItemBean.RecordsBean, BaseViewHolder>(R.layout.item_share) {
            @Override
            protected void convert(BaseViewHolder helper, final ShareItemBean.RecordsBean item) {
                helper.setText(R.id.tv_user_info,item.getDeptName()+"."+item.getUserName());
                helper.setText(R.id.tv_user_tel,item.getUserTel());
                helper.setText(R.id.tv_share_content,item.getShareContent());
                String documentUrl = item.getDocumentUrl();
                if(!TextUtils.isEmpty(documentUrl)){
                    helper.setVisible(R.id.ly_file,true);
                    helper.setText(R.id.tv_file_content, documentUrl.substring(documentUrl.lastIndexOf("/")+1));
                    helper.setText(R.id.tv_file_type,documentUrl.substring(documentUrl.lastIndexOf(".")+1).toUpperCase());
                    helper.getView(R.id.ly_file).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openFile(item.getDocumentUrl());
                        }
                    });
                }else{
                    helper.setVisible(R.id.ly_file,false);
                }
                if(!TextUtils.isEmpty(item.getShareUrl())){
                    helper.setVisible(R.id.ly_h5,true);
                    helper.setText(R.id.tv_h5_content, TextUtils.isEmpty(item.getShareTitle())?"分享链接":item.getShareTitle());
                    Glide.with(ShareActivity.this).load(item.getShareImg()).placeholder(R.drawable.img_link_null).into((ImageView) helper.getView(R.id.img_h5_content));
                    helper.getView(R.id.ly_h5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ShareActivity.this,CommonWebViewActivity.class);
                            intent.putExtra("url", item.getShareUrl());
                            intent.putExtra("title", item.getShareTitle());
                            startActivity(intent);
                        }
                    });
                }else{
                    helper.setVisible(R.id.ly_h5,false);
                }
            }
        };

        recycleShare.setLayoutManager(new LinearLayoutManager(this));
        recycleShare.setAdapter(adapter);

    }

    private void getdate() {
        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());
        requestBody.setPage(1+"");
        requestBody.setPageSize(100+"");
        RequestUtil.createApi().selectShare(requestBody).compose(RequestUtil.<ShareItemBean>handleResult()).subscribe(
                new CommonsSubscriber<ShareItemBean>() {
                    @Override
                    protected void onSuccess(ShareItemBean shareItemBean) {
                        adapter.setNewData(shareItemBean.getRecords());
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        getdate();
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

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new RequestBody();
                requestBody.setUserId(APP.getUserId());
                requestBody.setPage(1+"");
                requestBody.setPageSize(100+"");
                RequestUtil.createApi().selectShare(requestBody).compose(RequestUtil.<ShareItemBean>handleResult()).subscribe(
                        new CommonsSubscriber<ShareItemBean>() {
                            @Override
                            protected void onSuccess(ShareItemBean shareItemBean) {
                                swipe.setRefreshing(false);
                                adapter.setNewData(shareItemBean.getRecords());
                            }
                        }
                );
            }
        },1000);
    }

    private void openFile(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }
}
