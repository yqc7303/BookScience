package com.yangqichao.bokuscience.business.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MenuFiveSixFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycle_function)
    RecyclerView recycleFunction;
    Unbinder unbinder;

    private LoginBean mParam1;

    private BaseQuickAdapter<LoginBean.ModuleDTOSBean,BaseViewHolder> adapter;


    public MenuFiveSixFragment() {
        // Required empty public constructor
    }

    public static MenuFiveSixFragment newInstance(LoginBean param1) {
        MenuFiveSixFragment fragment = new MenuFiveSixFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (LoginBean) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_five_six, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BaseQuickAdapter<LoginBean.ModuleDTOSBean, BaseViewHolder>(R.layout.item_function,mParam1.getModuleDTOS()) {
            @Override
            protected void convert(BaseViewHolder helper, final LoginBean.ModuleDTOSBean item) {
                helper.setText(R.id.tv_function,item.getName());
                helper.setImageResource(R.id.img_function,ShowMenuUtil.getImageSmall(item.getCode()));
                helper.getView(R.id.img_function).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(ShowMenuUtil.getClass(getActivity(),item));
                    }
                });
            }
        };

        recycleFunction.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recycleFunction.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
