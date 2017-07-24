package com.yangqichao.bokuscience.business.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.library.StatusBarUtil;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;
import com.yangqichao.bokuscience.common.base.BaseActivity;
import com.yangqichao.commonlib.event.MarkEvent;
import com.yangqichao.commonlib.event.RxBus;
import com.yangqichao.commonlib.util.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SetFuncationActivity extends BaseActivity {


    @BindView(R.id.recycle_function)
    RecyclerView recycleFunction;

    String mark;
    private String[] split;
    private List<LoginBean.ModuleDTOSBean> dtosBeanList;
    private BaseQuickAdapter<LoginBean.ModuleDTOSBean,BaseViewHolder> adapter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_set_funcation;
    }

    public static void startAction(Context context, LoginBean loginBean){
        Intent intent = new Intent(context,SetFuncationActivity.class);
        intent.putExtra("bean",loginBean);
        context.startActivity(intent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarUtil.setTransparent(this);

        dtosBeanList = ((LoginBean) getIntent().getSerializableExtra("bean")).getModuleDTOS();

        mark = PreferenceUtils.getPrefString(this,"mark","");
        if(!TextUtils.isEmpty(mark)){
            split = mark.split("-");
            for(LoginBean.ModuleDTOSBean dtosBean:dtosBeanList){
                for(int i = 0;i<split.length;i++){
                    if(split[i].equals(dtosBean.getCode())){
                        dtosBean.setGone(true);
                    }
                }
            }
        }


        adapter = new BaseQuickAdapter<LoginBean.ModuleDTOSBean, BaseViewHolder>(R.layout.item_function_edit) {
            @Override
            protected void convert(final BaseViewHolder helper, final LoginBean.ModuleDTOSBean item) {
                helper.setText(R.id.tv_function,item.getName());
                helper.setImageResource(R.id.img_function,ShowMenuUtil.getImageSmall(item.getCode()));
                if(item.isGone()){
                    helper.setImageResource(R.id.img_visible,R.drawable.icon_unshow);
                }else{
                    helper.setImageResource(R.id.img_visible,R.drawable.icon_show);
                }
                helper.getView(R.id.img_visible).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(item.isGone()){
                            helper.setImageResource(R.id.img_visible,R.drawable.icon_show);
                            showToast("已显示功能");
                            item.setGone(false);
                        }else{
                            helper.setImageResource(R.id.img_visible,R.drawable.icon_unshow);
                            showToast("已隐藏功能");
                            item.setGone(true);
                        }
                    }
                });
            }
        };
        adapter.setNewData(dtosBeanList);
        recycleFunction.setLayoutManager(new GridLayoutManager(this,3));
        recycleFunction.setAdapter(adapter);

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        back();
    }

    private void back() {
        int i = 0;
        StringBuilder builder = new StringBuilder();
        for(LoginBean.ModuleDTOSBean dtosBean:dtosBeanList){
            if(dtosBean.isGone()){
                builder.append(dtosBean.getCode()+"-");
                i++;
            }
        }

        if(i == dtosBeanList.size()){
            showToast("至少保留一个功能");
            return;
        }

        String mk = builder.toString();
        if(!TextUtils.isEmpty(mk)){
            mk = mk.substring(0,mk.length()-1);
            PreferenceUtils.setPrefString(this,"mark",mk);
        }else {
            PreferenceUtils.setPrefString(this,"mark","");
        }

        RxBus.getDefault().post(new MarkEvent());
        finish();
    }


    @Override
    public void onBackPressed() {
        back();

    }
}
