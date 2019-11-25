package com.example.tripmate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripmate.Share.HttpShareInfoGetList;
import com.example.tripmate.Share.ShareRecyclerAdapter;
import com.example.tripmate.Share.TripRouteDataInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class fragmentActivity3  extends Fragment {

    View shareFrament;
    private Button getBtn;
    private ShareRecyclerAdapter adapter;
    private ArrayList<TripRouteDataInfo> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shareFrament = inflater.inflate(R.layout.fragment_main3, container, false);

        init();

        return shareFrament;
    }

    private void init() {
        RecyclerView recyclerView = shareFrament.findViewById(R.id.Share_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        adapter = new ShareRecyclerAdapter(getData(), new ShareRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast myToast = Toast.makeText(getContext(), "하잇", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<TripRouteDataInfo> getData() {
        dataList = new ArrayList<>();
        dataList.addAll(jsonParserForGetSharelist());
        return dataList;
    }

    //제이슨 파싱해서 데이터 리스트 얻어오는 부분
    private ArrayList<TripRouteDataInfo> jsonParserForGetSharelist() {

        HttpShareInfoGetList httpShareInfoList = new HttpShareInfoGetList();
        HttpShareInfoGetList.sendTask send = httpShareInfoList.new sendTask();

        String result = null;

        ArrayList<TripRouteDataInfo> list = new ArrayList<TripRouteDataInfo>();

        try {
            result = send.execute().get();
            JSONArray jarray = null;

            jarray = new JSONObject(result).getJSONArray("list");

            if (jarray != null) {

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jsonObject = jarray.getJSONObject(i);

                    String code = jsonObject.getString("planCode");
                    String place = jsonObject.getString("locationTitle");
                    String title = jsonObject.getString("shareTitle");
                    String contents = jsonObject.getString("content");
                    TripRouteDataInfo model = new TripRouteDataInfo(code, place, title, contents);
                    list.add(model);
                }

                return list;
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

}
