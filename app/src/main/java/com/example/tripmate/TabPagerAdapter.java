/* 내가 수정할 부분 */

package com.example.tripmate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.example.tripmate.Board.fragmentBoard;
import com.example.tripmate.Chat.fragmentChatroom;
import com.example.tripmate.Chat.fragmentFriend;

import java.util.ArrayList;

//FragmentStatePagerAdapter
public class TabPagerAdapter extends FragmentPagerAdapter {
    //  private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private int count;
    public TabPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }


    @Override
    public Fragment getItem(int position) {

        String nickname = fragmentActivity4.getNickname();
        System.out.println("tabpageradapter  " + nickname);

        Bundle bundle = new Bundle();
        bundle.putString("nickname",nickname);

        fragmentFriend tabFragment1 = new fragmentFriend();
        fragmentBoard tabFragment2 = new fragmentBoard();
        fragmentChatroom tabFragment3 = new fragmentChatroom();

        switch (position) {
            case 0:

                return tabFragment1;

            case 1:

                tabFragment2.setArguments(bundle);
                return tabFragment2;

            case 2:
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
