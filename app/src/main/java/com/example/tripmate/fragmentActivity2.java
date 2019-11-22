package com.example.tripmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripmate.Plan.AddListActivity;
import com.example.tripmate.Plan.DesignClassFile.PlanListAdapter;
import com.example.tripmate.Plan.DataClassFile.PlanListModel;
import com.example.tripmate.Plan.SelectPlanList;

import java.util.ArrayList;

public class fragmentActivity2  extends Fragment {

    private ArrayList<PlanListModel> planlist;
    private PlanListAdapter adapter;
    private View view;
    private String nickname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main2, container, false);
        nickname = SaveSharedPreference.getNickName(getContext()).toString();

        init();

        final FloatingActionButton addBt = (FloatingActionButton)view.findViewById(R.id.fab_addlist);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), AddListActivity.class);
                startActivity(intent1);
            }
        });

        return view;
    }

    private void init() {
        RecyclerView recyclerView = view.findViewById(R.id.rc_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        SelectPlanList data = new SelectPlanList();

        adapter = new PlanListAdapter(data.getList(nickname));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
    }

}
