package com.yangqichao.bokuscience.business.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangqichao.bokuscience.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MenuFourFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    Unbinder unbinder;

    private String mParam1;


    public MenuFourFragment() {
        // Required empty public constructor
    }

    public static MenuFourFragment newInstance(String param1, String param2) {
        MenuFourFragment fragment = new MenuFourFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_four, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_function_2, R.id.img_function_3, R.id.img_function_4, R.id.img_function_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_function_2:

                break;
            case R.id.img_function_3:
                break;
            case R.id.img_function_4:
                break;
            case R.id.img_function_1:

                break;
        }
    }
}
