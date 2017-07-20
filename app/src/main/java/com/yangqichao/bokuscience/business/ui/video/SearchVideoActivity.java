package com.yangqichao.bokuscience.business.ui.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.InitVideoBean;
import com.yangqichao.bokuscience.business.bean.VideoListBean;
import com.yangqichao.bokuscience.common.APP;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestBody;
import com.yangqichao.bokuscience.common.net.RequestUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchVideoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.tv_video_type)
    TextView tvVideoType;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycle_video_search)
    RecyclerView recycleVideoSearch;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    PopupWindow popupWindow;
    RecyclerView recyclerView;

    List<InitVideoBean.SubjectListBean> list;
    private BaseQuickAdapter<InitVideoBean.SubjectListBean,BaseViewHolder> adapterType;
    private BaseQuickAdapter<VideoListBean.RecordsBean,BaseViewHolder> adapter;
    InitVideoBean.SubjectListBean bean;
    private List<VideoListBean.RecordsBean> records;

    public static void startAction(Context context, List<InitVideoBean.SubjectListBean> list){
        Intent intent = new Intent(context,SearchVideoActivity.class);
        intent.putExtra("list", (Serializable) list);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_search_video;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        swipe.setColorSchemeResources(R.color.base_orange);
        swipe.setOnRefreshListener(this);

        etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    seatchData();
                    hideSoftInputView();
                }
                return false;
            }
        });

        initType();

        adapter = new BaseQuickAdapter<VideoListBean.RecordsBean, BaseViewHolder>(R.layout.item_video_list) {
            @Override
            protected void convert(BaseViewHolder helper, VideoListBean.RecordsBean item) {
                helper.setText(R.id.tv_video_name,item.getTitle());
            }
        };

        recycleVideoSearch.setLayoutManager(new LinearLayoutManager(this));
        recycleVideoSearch.setAdapter(adapter);
        recycleVideoSearch.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoDetailActivity.startAction(SearchVideoActivity.this,records.get(position));

            }
        });
        adapter.setEmptyView(R.layout.empty_video_view,recycleVideoSearch);
        seatchData();
    }

    private void initType() {
        list = (List<InitVideoBean.SubjectListBean>) getIntent().getSerializableExtra("list");
        bean = new InitVideoBean.SubjectListBean();
        bean.setSubName("全部");
        list.add(0,bean);
        adapterType = new BaseQuickAdapter<InitVideoBean.SubjectListBean,BaseViewHolder>(R.layout.item_param) {
            @Override
            protected void convert(BaseViewHolder helper, InitVideoBean.SubjectListBean item) {
                helper.setText(R.id.tv_param, item.getSubName());
            }
        };

        adapterType.setNewData(list);
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterType);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                bean = (InitVideoBean.SubjectListBean) adapter.getData().get(position);
                tvVideoType.setText(bean.getSubName());
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(recyclerView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                seatchData();
            }
        });
    }

    private void seatchData() {
        String key = etSearch.getText().toString();
        RequestBody requestBody = new RequestBody();
        requestBody.setUserId(APP.getUserId());
        if(!bean.getSubName().equals("全部")){
            requestBody.setSubjectId(bean.getId()+"");
        }
        requestBody.setSearch(key);
        requestBody.setPage(1+"");
        requestBody.setPageSize(100+"");
        RequestUtil.createApi().selectVideoList(requestBody).compose(RequestUtil.<VideoListBean>handleResult())
                .subscribe(new CommonsSubscriber<VideoListBean>() {
                    @Override
                    protected void onSuccess(VideoListBean videoListBean) {
                        records = videoListBean.getRecords();
                        adapter.setNewData(records);
                    }
                });
    }


    @OnClick({R.id.img_back, R.id.tv_video_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_video_type:
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else{
                    popupWindow.showAsDropDown(tvVideoType);
                }

                break;
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String key = etSearch.getText().toString();
                RequestBody requestBody = new RequestBody();
                requestBody.setUserId(APP.getUserId());
                if(!bean.getSubName().equals("全部")){
                    requestBody.setSubjectId(bean.getId()+"");
                }
                requestBody.setSearch(key);
                requestBody.setPage(1+"");
                requestBody.setPageSize(100+"");
                RequestUtil.createApi().selectVideoList(requestBody).compose(RequestUtil.<VideoListBean>handleResult())
                        .subscribe(new CommonsSubscriber<VideoListBean>() {
                            @Override
                            protected void onSuccess(VideoListBean videoListBean) {
                                swipe.setRefreshing(false);
                                records = videoListBean.getRecords();
                                adapter.setNewData(records);
                            }
                        });
            }
        },1000);
    }
}
