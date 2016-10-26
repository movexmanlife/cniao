package com.cniao5.app.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cniao5.app.R;
import com.cniao5.app.adapter.FixedPagerAdapter;
import com.cniao5.app.common.DefineView;
import com.cniao5.app.entity.CategoriesEntity;
import com.cniao5.app.base.BaseFragment;
import com.cniao5.app.ui.MainActivity;
import com.cniao5.app.util.CategoryDataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MainContentFragment extends BaseFragment implements DefineView, ViewPager.OnPageChangeListener {
    private View mView;
    private TabLayout tab_layout;
    private ViewPager info_viewpager;
    private FixedPagerAdapter fixedPagerAdapter;
    private List<Fragment> fragments;
    private static List<CategoriesEntity> categoriesEntities = CategoryDataUtil.getCategoryBeans();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_main_content, container, false);
            initView();
            initValidata();
            initListener();
            bindData();
        }
        return mView;
    }

    @Override
    public void initView() {
        tab_layout = (TabLayout) mView.findViewById(R.id.tab_layout);
        info_viewpager = (ViewPager) mView.findViewById(R.id.info_viewpager);
    }

    @Override
    public void initValidata() {
        fixedPagerAdapter = new FixedPagerAdapter(getChildFragmentManager());
        fixedPagerAdapter.setCategoriesEntities(categoriesEntities);
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < categoriesEntities.size(); i++) {
            BaseFragment fragment = null;
            if (i == 0) {
                fragment = HomeFragment.newInstance(categoriesEntities.get(i));
            } else {
                fragment = PageFragment.newInstance(categoriesEntities.get(i));
            }
            fragments.add(fragment);
        }
        fixedPagerAdapter.setFragments(fragments);

        info_viewpager.setAdapter(fixedPagerAdapter);
        tab_layout.setupWithViewPager(info_viewpager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void initListener() {
        info_viewpager.setOnPageChangeListener(this);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            ((MainActivity) getActivity()).getmDragLayout().setIsDrag(true);
        } else {
            ((MainActivity) getActivity()).getmDragLayout().setIsDrag(false);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
