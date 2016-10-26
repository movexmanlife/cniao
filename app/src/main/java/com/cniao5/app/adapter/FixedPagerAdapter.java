package com.cniao5.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.cniao5.app.entity.CategoriesEntity;

import java.util.List;

/**
 * ViewPager Fragment自定义适配器，其中管理每个页面(Fragment集合)和Tab显示标题
 */
public class FixedPagerAdapter extends FragmentStatePagerAdapter {
    private List<CategoriesEntity> categoriesEntities;

    public void setCategoriesEntities(List<CategoriesEntity> categoriesEntities) {
        this.categoriesEntities = categoriesEntities;
    }

    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public FixedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {

        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoriesEntities.get(position % categoriesEntities.size()).getTitle();
    }
}
