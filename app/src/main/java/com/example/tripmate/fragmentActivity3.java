package com.example.tripmate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripmate.Share.ShareRecyclerAdapter;
import com.example.tripmate.Share.TripRouteDataInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fragmentActivity3  extends Fragment {

    View shareFrament;
    private ShareRecyclerAdapter adapter;
    private ArrayList<TripRouteDataInfo> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shareFrament = inflater.inflate(R.layout.fragment_main3, container, false);

        init();
        getData();

        return shareFrament;
    }

    private void init() {
        RecyclerView recyclerView = shareFrament.findViewById(R.id.Share_recycler);
        Log.i("ddd","dddddddddd");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new ShareRecyclerAdapter(getData());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private ArrayList<TripRouteDataInfo> getData() {

        dataList = new ArrayList<>();

        List<String> share_title = Arrays.asList(
                "국화1", "사막2", "수국3",
                "국화4", "사막5", "수국6",
                "국화7", "사막8", "수국9",
                "국화10", "사막11", "수국12",
                "국화13", "사막14", "수국15",
                "국화16", "사막17", "수국18",
                "국화19", "사막20", "수국21",
                "국화22", "사막23", "수국24",
                "국화25", "사막26", "수국27",
                "국화28", "사막29", "수국30",
                "국화31", "사막32", "수국33",
                "국화34", "사막35", "수국36",
                "국화37", "사막38", "수국39"
        );

        List<String> share_location_title = Arrays.asList(
                "국화1", "사막2", "수국3",
                "국화4", "사막5", "수국6",
                "국화7", "사막8", "수국9",
                "국화10", "사막11", "수국12",
                "국화13", "사막14", "수국15",
                "국화16", "사막17", "수국18",
                "국화19", "사막20", "수국21",
                "국화22", "사막23", "수국24",
                "국화25", "사막26", "수국27",
                "국화28", "사막29", "수국30",
                "국화31", "사막32", "수국33",
                "국화34", "사막35", "수국36",
                "국화37", "사막38", "수국39"
        );

        List<String> share_content = Arrays.asList(
                "국화1", "사막2", "수국3",
                "국화4", "사막5", "수국6",
                "국화7", "사막8", "수국9",
                "국화10", "사막11", "수국12",
                "국화13", "사막14", "수국15",
                "국화16", "사막17", "수국18",
                "국화19", "사막20", "수국21",
                "국화22", "사막23", "수국24",
                "국화25", "사막26", "수국27",
                "국화28", "사막29", "수국30",
                "국화31", "사막32", "수국33",
                "국화34", "사막35", "수국36",
                "국화37", "사막38", "수국39"
        );



        for (int i = 0; i < share_title.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            TripRouteDataInfo data = new TripRouteDataInfo();

            data.setTripTitle(share_title.get(i));
            data.setTripLocation(share_location_title.get(i));
            data.setTripDescribe(share_content.get(i));

            dataList.add(data);
        }
        String a = String.valueOf(dataList.size());
        Log.i("TDB1", a);

        return dataList;

    }
}
