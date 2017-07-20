package com.yangqichao.bokuscience;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.business.bean.SampleBean;
import com.yangqichao.bokuscience.business.bean.ScienceDynamicBean;
import com.yangqichao.bokuscience.business.ui.login.LoginActivity;
import com.yangqichao.bokuscience.business.ui.main.MenuFiveSixFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuFourFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuOneFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuSixMoreFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuThreeFragment;
import com.yangqichao.bokuscience.business.ui.main.MenuTwoFragment;
import com.yangqichao.bokuscience.business.ui.main.SetFuncationActivity;
import com.yangqichao.bokuscience.business.ui.message.DynamicListActivity;
import com.yangqichao.bokuscience.business.ui.mine.MineActivity;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.bokuscience.common.net.CommonsSubscriber;
import com.yangqichao.bokuscience.common.net.RequestUtil;
import com.yangqichao.bokuscience.common.widget.VerticalTextview;
import com.yangqichao.commonlib.event.EventSubscriber;
import com.yangqichao.commonlib.event.MarkEvent;
import com.yangqichao.commonlib.event.RxBus;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.fragment_main)
    FrameLayout fragmentMain;
    @BindView(R.id.main)
    ConstraintLayout main;
    private BaseQuickAdapter<SampleBean, BaseViewHolder> adapter;
    private List<SampleBean> sampleBeanList;


    private LoginBean loginBean;

    public static void startAction(Context context, LoginBean loginBean) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("user", loginBean);
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
        if (loginBean == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }




        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);

        if (loginBean != null) {
            tvName.setText(loginBean.getHospitalName() + "");
            Glide.with(this).load(loginBean.getHospitalLogo())
                    .bitmapTransform(new CropCircleTransformation(this))
                    .into(ivLogo);
        }


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.item_home:
                                drawerMain.closeDrawers();
                                break;
                            case R.id.item_function:
                                SetFuncationActivity.startAction(MainActivity.this, loginBean);
                                break;
                            case R.id.item_account:
                                startActivity(new Intent(MainActivity.this, MineActivity.class));
                                break;
                        }
//                        menuItem.setCheckable(true);//设置选项可选
//                        menuItem.setChecked(true);//设置选型被选中
                        drawerMain.closeDrawers();//关闭侧边菜单栏
                        return true;
                    }
                });
        if (loginBean.getModuleDTOSUser() != null) {
            getFragment();
        }
        initGongGao();


        RxBus.getDefault().toObservable(MarkEvent.class)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new EventSubscriber<MarkEvent>() {
                    @Override
                    public void onNextDo(MarkEvent markEvent) {
                        String mark = PreferenceUtils.getPrefString(MainActivity.this, "mark", "");
                        if (!TextUtils.isEmpty(mark)) {
                            String[] split = mark.split("-");
                            List<LoginBean.ModuleDTOSBean> beanList = new ArrayList<LoginBean.ModuleDTOSBean>();
                            for (LoginBean.ModuleDTOSBean dtosBean : loginBean.getModuleDTOS()) {
                                dtosBean.setGone(false);
                                for (int i = 0; i < split.length; i++) {
                                    if (split[i].equals(dtosBean.getCode())) {
                                        dtosBean.setGone(true);
                                    }
                                }
                            }
                            for (LoginBean.ModuleDTOSBean dtosBean : loginBean.getModuleDTOS()) {
                                if (!dtosBean.isGone()) {
                                    beanList.add(dtosBean);
                                }
                            }
                            loginBean.setModuleDTOSUser(beanList);
                        } else {
                            loginBean.setModuleDTOSUser(loginBean.getModuleDTOS());
                        }
                        int function_size = loginBean.getModuleDTOSUser().size();
                        if (function_size > 6) function_size = 7;
                        Fragment fragment = getFragment(function_size);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main, fragment).commitAllowingStateLoss();
                    }
                });
        showBrithday();

    }

    private void showBrithday() {
        if (loginBean.getIsBirthday() == 1) {
            String today = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
            if (PreferenceUtils.getPrefBoolean(this, today, true)) {
                final View view = LayoutInflater.from(this).inflate(R.layout.brithday, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(view);
                final AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView textView = (TextView) view.findViewById(R.id.tv_brithday_name);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_brithday);
                textView.setText(String.format(getString(R.string.brithday), loginBean.getName()));

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                PreferenceUtils.setPrefBoolean(this, today, false);
            }


        }
    }


    private void getFragment() {
        int function_size = loginBean.getModuleDTOSUser().size();
        if (function_size > 6) function_size = 7;
        Fragment fragment = getFragment(function_size);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, fragment).commitAllowingStateLoss();

    }

    @Nullable
    private Fragment getFragment(int function_size) {
        Fragment fragment = null;
        switch (function_size) {
            case 1:
                fragment = MenuOneFragment.newInstance(loginBean);
                break;
            case 2:
                fragment = MenuTwoFragment.newInstance(loginBean);
                break;
            case 3:
                fragment = MenuThreeFragment.newInstance(loginBean);
                break;
            case 4:
                fragment = MenuFourFragment.newInstance(loginBean);
                break;
            case 5:
                fragment = MenuFiveSixFragment.newInstance(loginBean);
                break;
            case 6:
                fragment = MenuFiveSixFragment.newInstance(loginBean);
                break;
            case 7:
                fragment = MenuSixMoreFragment.newInstance(loginBean);
                break;
        }
        return fragment;
    }


    private void initGongGao() {
        RequestUtil.createApi().getbyuser(PreferenceUtils.getPrefString(this, "uId", ""))
                .compose(RequestUtil.<List<ScienceDynamicBean>>handleResult())
                .subscribe(new CommonsSubscriber<List<ScienceDynamicBean>>() {
                    @Override
                    protected void onSuccess(final List<ScienceDynamicBean> scienceDynamicBeanList) {
                        verticalTextview.setTextList(scienceDynamicBeanList);
                        verticalTextview.setText(16, 0, ContextCompat.getColor(MainActivity.this, R.color.white));//设置属性
                        verticalTextview.setTextStillTime(2500);//设置停留时长间隔
                        verticalTextview.setAnimTime(300);//设置进入和退出的时间间隔
                        verticalTextview.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                startActivity(new Intent(MainActivity.this, DynamicListActivity.class));
                            }
                        });
                        verticalTextview.startAutoScroll();
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
