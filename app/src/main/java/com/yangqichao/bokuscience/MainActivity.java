package com.yangqichao.bokuscience;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.igexin.sdk.PushManager;
import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.bean.SampleBean;
import com.yangqichao.bokuscience.business.bean.ScienceDynamicBean;
import com.yangqichao.bokuscience.business.service.GetuiIntentService;
import com.yangqichao.bokuscience.business.service.GetuiPushService;
import com.yangqichao.bokuscience.business.ui.main.MenuFiveSixFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuFourFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuOneFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuSixMoreFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuThreeFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuTwoFragment;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.bokuscience.common.widget.VerticalTextview;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_demo)
    RecyclerView recyclerDemo;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.verticalTextview)
    VerticalTextview verticalTextview;
    private BaseQuickAdapter<SampleBean, BaseViewHolder> adapter;
    private List<SampleBean> sampleBeanList;
    private int function_size;


    private LoginBean loginBean;
    public static void startAction(Context context, LoginBean loginBean){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("user",loginBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForDrawerLayout(this, drawerMain, 0);

        loginBean = (LoginBean) getIntent().getSerializableExtra("user");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);


//        showToast("DEBUG:" + BuildConfig.DEBUG + "\n BUILD_TYPE:" + BuildConfig.BUILD_TYPE + "\n VERSION_CODE:" + BuildConfig.VERSION_CODE);
//        showToast(BuildConfig.BaseUrl);
//
//        sampleBeanList = new ArrayList<>();
//        sampleBeanList.add(new SampleBean("ePud阅读器", EpubReaderActivity.class));
//        sampleBeanList.add(new SampleBean("ePud", HomeActivity.class));
//
//        sampleBeanList.add(new SampleBean("高德定位", LocationActivity.class));
//
//        adapter = new BaseQuickAdapter<SampleBean, BaseViewHolder>(R.layout.item_demo, sampleBeanList) {
//            @Override
//            protected void convert(BaseViewHolder helper, SampleBean item) {
//                helper.setText(R.id.sample_name, item.getName());
//            }
//        };
//
//        recyclerDemo.setLayoutManager(new LinearLayoutManager(this));
//        recyclerDemo.setAdapter(adapter);
//        recyclerDemo.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Class aClass = sampleBeanList.get(position).getActivity();
//                Intent intent = new Intent(MainActivity.this, aClass);
//                if (aClass == FolioActivity.class) {
//                    intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_TYPE, FolioActivity.EpubSourceType.ASSESTS);
//                    intent.putExtra(FolioActivity.INTENT_EPUB_SOURCE_PATH, "varun.epub");
//                }
//                startActivity(intent);
//            }
//        });

        PushManager.getInstance().initialize(this.getApplicationContext(), GetuiPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetuiIntentService.class);
/////////////////////////

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_home:
                                showToast("home");
                                break;
                            case R.id.item_function:
                                showToast("function");
                                break;
                            case R.id.item_account:
                                showToast("account");
                                break;
                        }
//                        menuItem.setCheckable(true);//设置选项可选
//                        menuItem.setChecked(true);//设置选型被选中
                        drawerMain.closeDrawers();//关闭侧边菜单栏
                        getFragment(function_size++);
                        return true;
                    }
                });

        ////////////////

        function_size = 4;

        getFragment(function_size);


    }

    private void getFragment(int function_size) {
        if (function_size >= 6) function_size = 6;
        Fragment fragment = null;
        switch (function_size) {
            case 1:
                fragment = new MenuOneFragment();
                break;
            case 2:
                fragment = new MenuTwoFragment();
                break;
            case 3:
                fragment = new MenuThreeFragment();
                break;
            case 4:
                fragment = new MenuFourFragment();
                break;
            case 5:
                fragment = new MenuFiveSixFragment();
                break;
            case 6:
                fragment = new MenuSixMoreFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, fragment).commitAllowingStateLoss();

    }


    private void initGongGao() {
        RequestUtil.createApi().getbyuser(PreferenceUtils.getPrefString(this,"uId",""))
                .compose(RequestUtil.<List<ScienceDynamicBean>>handleResult())
                .subscribe(new CommonsSubscriber<List<ScienceDynamicBean>>() {
                    @Override
                    protected void onSuccess(final List<ScienceDynamicBean> scienceDynamicBeanList) {
                        verticalTextview.setTextList(scienceDynamicBeanList);
                        verticalTextview.setText(13, 0, ContextCompat.getColor(MainActivity.this, R.color.base_text_white));//设置属性
                        verticalTextview.setTextStillTime(2500);//设置停留时长间隔
                        verticalTextview.setAnimTime(300);//设置进入和退出的时间间隔
                        verticalTextview.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
//                                mainActivity.setBottomNavigationBarPosition(1);
                            }
                        });
                        verticalTextview.startAutoScroll();
                    }
                });
    }
}
