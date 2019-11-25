/* 화면 : 메인 */

package com.example.tripmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tripmate.Main.MainSearchListActivity;
import com.example.tripmate.Plan.HttpPlanRouteList;

public class fragmentActivity1  extends Fragment {

    View mainFragment;
    ImageButton textBtn;
    ImageButton memberAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainFragment = inflater.inflate(R.layout.fragment_main1, container, false);


        //검색창을 누르면 나타나는 버튼
        textBtn = (ImageButton)mainFragment.findViewById(R.id.et_sch);
        textBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(getActivity(), MainSearchListActivity.class);
                startActivity(intent1);
            }
        });

        //검색창을 누르면 나타나는 버튼
        memberAdd = (ImageButton)mainFragment.findViewById(R.id.main_member_add_btn);
        memberAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent1 = new Intent(getActivity(), HttpPlanRouteList.class);
                startActivity(intent1);
            }
        });

        return mainFragment;
    }

}
