package com.example.tripmate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int count;
    List<Fragment> listFragments;

    //생성자
    public TabPagerAdapter(FragmentManager fragMNG, List<Fragment> listFragments) {
        super(fragMNG);
        this.listFragments = listFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
