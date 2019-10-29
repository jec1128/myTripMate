package com.example.tripmate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tripmate.Board.fragmentBoard;
import com.example.tripmate.Chat.fragmentChatroom;
import com.example.tripmate.Chat.fragmentFriend;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                fragmentFriend tabFragment3 = new fragmentFriend();
                return tabFragment3;
            case 1:
                fragmentBoard tabFragment2 = new fragmentBoard();
                fragmentActivity4 fragment4 = new fragmentActivity4().newInstance();
                String nickname = fragment4.getNickname();
                System.out.println("tabpageradapter  " + nickname);
                Bundle bundle = new Bundle();
                bundle.putString("nickname",nickname);
                tabFragment2.setArguments(bundle);
                return tabFragment2;
            case 2:
                fragmentChatroom tabFragment1 = new fragmentChatroom();
                return tabFragment1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
