package com.yangqichao.bokuscience.business.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MenuSixMoreFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private LoginBean mParam1;
    private List<LoginBean.ModuleDTOSBean> moduleDTOS;
    private List<MenuFiveSixFragment> fragments;

    public MenuSixMoreFragment() {
        // Required empty public constructor
    }

    public static MenuSixMoreFragment newInstance(LoginBean param1) {
        MenuSixMoreFragment fragment = new MenuSixMoreFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu_six_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moduleDTOS = mParam1.getModuleDTOS();
        fragments = new ArrayList<>();
        for(int i = 0; i< moduleDTOS.size()/6+1; i++){
            int size = i < (moduleDTOS.size()/6)?6:moduleDTOS.size()%6;
            LoginBean loginBean = new LoginBean();
            List<LoginBean.ModuleDTOSBean> beanList = new ArrayList<>();
            for(int j = 0;j<size;j++){
                beanList.add(moduleDTOS.get(6*i+j));
            }
            loginBean.setModuleDTOS(beanList);
            fragments.add(MenuFiveSixFragment.newInstance(loginBean));
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
