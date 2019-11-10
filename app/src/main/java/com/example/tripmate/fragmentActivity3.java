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

        List<String> share_title = Arrays.asList("국화", "사막", "수국");

        List<String> share_location_title = Arrays.asList(
                "이 꽃은 국화입니다.",
                "여기는 사막입니다.",
                "이 꽃은 수국입니다."
        );

        List<String> share_content = Arrays.asList(
                "aaaaaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbb",
                "cccccccccccccccccccc"
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
