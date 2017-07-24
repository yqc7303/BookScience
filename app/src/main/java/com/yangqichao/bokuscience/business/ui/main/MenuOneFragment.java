package com.yangqichao.bokuscience.business.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yangqichao.bokuscience.R;
import com.yangqichao.bokuscience.business.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MenuOneFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_function)
    TextView tvFunction;
    Unbinder unbinder;

    private LoginBean mParam1;
    private LoginBean.ModuleDTOSBean moduleDTOSBean;


    public MenuOneFragment() {
        // Required empty public constructor
    }

    public static MenuOneFragment newInstance(LoginBean param1) {
        MenuOneFragment fragment = new MenuOneFragment();
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
        View view = inflater.inflate(R.layout.fragment_menu_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moduleDTOSBean = mParam1.getModuleDTOSUser().get(0);
        if(!TextUtils.isEmpty(moduleDTOSBean.getImgUrl())&&!ShowMenuUtil.isMainFuncation(moduleDTOSBean.getCode())){
            Glide.with(getActivity()).load(moduleDTOSBean.getImgUrl()).into(imageView);
        }else{
            imageView.setImageResource(ShowMenuUtil.getImage(moduleDTOSBean.getCode()));
        }
        tvFunction.setText(moduleDTOSBean.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imageView)
    public void onViewClicked() {
        startActivity(ShowMenuUtil.getClass(getActivity(),moduleDTOSBean));
    }
}
