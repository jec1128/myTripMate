package com.example.tripmate;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.tripmate.Board.fragmentBoard;
import com.example.tripmate.Chat.fragmentChatroom;


public class fragmentActivity4 extends Fragment {
    private static String nickname;
    View myFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private int[] icons = new int []{R.drawable.img_tab_board,
            R.drawable.img_tab_chat,
            R.drawable.img_tab_friend};

    @SuppressLint("ValidFragment")
    public fragmentActivity4(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_main4, container, false);

        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
        }
        System.out.println("fragment4 " + nickname);

        viewPager = myFragment.findViewById(R.id.Viewpager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        toolbar = myFragment.findViewById(R.id.toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Initializing the TabLayout
       /* tabLayout.addTab(tabLayout.newTab().setText("friend"));
        tabLayout.addTab(tabLayout.newTab().setText("board"));
        tabLayout.addTab(tabLayout.newTab().setText("chat"));*/
        tabLayout.addTab(tabLayout.newTab().setIcon(icons[2]));
        tabLayout.addTab(tabLayout.newTab().setIcon(icons[0]));
        tabLayout.addTab(tabLayout.newTab().setIcon(icons[1]));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Initializing ViewPager

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(((AppCompatActivity)getActivity()).getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return myFragment;
    }
    public static fragmentActivity4 newInstance(){
        fragmentActivity4 fragment = new fragmentActivity4();
        Bundle bundle = new Bundle();
        bundle.putString("nickname",getNickname());
        return fragment;
    }

    public static String getNickname() {
        return nickname;
    }
}
