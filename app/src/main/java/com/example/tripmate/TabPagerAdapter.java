package com.example.tripmate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tripmate.Board.fragmentBoard;
import com.example.tripmate.Chat.fragmentChatroom;
import com.example.tripmate.Chat.fragmentFriend;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    //  private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private int count;
    public TabPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }



    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                fragmentFriend tabFragment1 = new fragmentFriend();
                return tabFragment1;
            case 1:
                fragmentBoard tabFragment2 = fragmentBoard.getInstance();
                String nickname = fragmentActivity4.getNickname();
                System.out.println("tabpageradapter  " + nickname);
                Bundle bundle = new Bundle();
                bundle.putString("nickname",nickname);
                tabFragment2.setArguments(bundle);
                return tabFragment2;
            case 2:
                fragmentChatroom tabFragment3 = new fragmentChatroom();
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
