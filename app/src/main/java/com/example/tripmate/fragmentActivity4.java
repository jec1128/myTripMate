package com.example.tripmate;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripmate.Board.fragmentBoard;
import com.example.tripmate.Chat.fragmentChatroom;
import com.example.tripmate.Chat.fragmentFriend;

import java.util.ArrayList;
import java.util.List;


public class fragmentActivity4 extends Fragment {
    private static String nickname;
    private static fragmentActivity4 instance;
    private View myFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    //탭메뉴 추가
    fragmentFriend tabFriend;
    fragmentBoard tabBoard;
    fragmentChatroom tabChat;

    private int[] icons = new int []{
            R.drawable.img_tab_friend,
            R.drawable.img_tab_chat,
            R.drawable.img_tab_board
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_main4, container, false);
        viewPager = (ViewPager) myFragment.findViewById (R.id.Viewpager);

        Bundle extra = this.getArguments();

        if(extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
        }
        System.out.println("fragment4 " + nickname);

        //각 탭에 대한 프래그먼트 추가
        List<Fragment> listFragments = new ArrayList<>();
        Log.i("111111","로그확인");
        tabBoard = new fragmentBoard();
        tabFriend = new fragmentFriend();
        tabChat = new fragmentChatroom();
        Log.i("222222","로그확인");
        listFragments.add(tabBoard);
        listFragments.add(tabFriend);
        listFragments.add(tabChat);
        Log.i("3333333","로그확인");

        TabPagerAdapter fragmentPagerAdapter = new TabPagerAdapter(getChildFragmentManager(),listFragments);
        Log.i("4444444","로그확인");
        viewPager.setAdapter(fragmentPagerAdapter);


        //뷰페이저 셋팅
        viewPager = myFragment.findViewById(R.id.Viewpager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(icons[0]));
        tabLayout.addTab(tabLayout.newTab().setIcon(icons[1]));
        tabLayout.addTab(tabLayout.newTab().setIcon(icons[2]));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        int a  = tab.getPosition();
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 1:
                        int b  = tab.getPosition();
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                    case 2 :
                        int c  = tab.getPosition();
                        viewPager.setCurrentItem(tab.getPosition());
                        break;
                }
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

    @SuppressLint("ValidFragment")
    private fragmentActivity4(){}

    public static fragmentActivity4 getInstance(){
        if(instance == null){
            instance = new fragmentActivity4();
            Bundle bundle = new Bundle();
            bundle.putString("nickname",getNickname());
            return instance;
        }
        return instance;
    }

    public static String getNickname() {
        return nickname;
    }

}