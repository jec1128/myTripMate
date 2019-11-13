package com.example.tripmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripmate.Plan.AddListActivity;
import com.example.tripmate.Plan.PlanListAdapter;
import com.example.tripmate.Plan.PlanListModel;
import com.example.tripmate.Plan.PlanTripActivity;

import java.util.ArrayList;

public class fragmentActivity2  extends Fragment {
    private RecyclerView recyclerView;
    private PlanListAdapter listAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rc_list);

        listAdapter = new PlanListAdapter();
        listAdapter.getList();
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

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
}
