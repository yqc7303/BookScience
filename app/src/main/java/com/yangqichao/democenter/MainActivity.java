package com.yangqichao.democenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.folioreader.activity.FolioActivity;
import com.igexin.sdk.PushManager;
import com.yangqichao.democenter.business.service.GetuiIntentService;
import com.yangqichao.democenter.business.service.GetuiPushService;
import com.yangqichao.democenter.business.bean.SampleBean;
import com.yangqichao.democenter.business.ui.EpubReaderActivity;
import com.yangqichao.democenter.business.ui.HomeActivity;
import com.yangqichao.democenter.business.ui.LocationActivity;
import com.yangqichao.democenter.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_demo)
    RecyclerView recyclerDemo;
    private BaseQuickAdapter<SampleBean, BaseViewHolder> adapter;
    private List<SampleBean> sampleBeanList;



    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showToast("DEBUG:" + BuildConfig.DEBUG + "\n BUILD_TYPE:" + BuildConfig.BUILD_TYPE + "\n VERSION_CODE:" + BuildConfig.VERSION_CODE);
        showToast(BuildConfig.BaseUrl);

        sampleBeanList = new ArrayList<>();
        sampleBeanList.add(new SampleBean("ePud阅读器", EpubReaderActivity.class));
        sampleBeanList.add(new SampleBean("ePud", HomeActivity.class));

        sampleBeanList.add(new SampleBean("高德定位", LocationActivity.class));

        adapter = new BaseQuickAdapter<SampleBean, BaseViewHolder>(R.layout.item_demo,sampleBeanList) {
            @Override
            protected void convert(BaseViewHolder helper, SampleBean item) {
                helper.setText(R.id.sample_name,item.getName());
            }
        };

        recyclerDemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerDemo.setAdapter(adapter);
        recyclerDemo.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Class aClass = sampleBeanList.get(position).getActivity();
                Intent intent = new Intent(MainActivity.this, aClass);
                if(aClass == FolioActivity.class){
                    intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE,FolioActivity.EpubSourceType.ASSESTS);
                    intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH,"varun.epub");
                }
                startActivity(intent);
            }
        });

        PushManager.getInstance().initialize(this.getApplicationContext(), GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);

    }






}
