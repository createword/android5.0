package com.example.winter.ddesignan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by WINTER on 2016/9/19.
 */
public class myAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listStr;

    public myAdapter(FragmentManager fm, List<Fragment> listStr) {
        super(fm);
        this.listStr = listStr;
    }

    @Override
    public Fragment getItem(int position) {
        return listStr.get(position);
    }

    @Override
    public int getCount() {
        return listStr.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listStr.get(position) + "页";
    }
}
