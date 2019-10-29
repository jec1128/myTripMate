package com.example.tripmate.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tripmate.R;
import com.example.tripmate.TourAPI.TripDataInfo;
import com.example.tripmate.TourAPI.TripInfoAPI;

import java.util.ArrayList;
import java.util.List;

public class MainKeywordActivity extends AppCompatActivity {
    private List<TripDataInfo> list = new ArrayList(); // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private KeywordListAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<TripDataInfo> arraylist;
    private TripInfoAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_search);

        ListView listview;
        final KeywordListAdapter adapter;

        //Adapter 생성
        adapter = new KeywordListAdapter();

        listview = (ListView) findViewById(R.id.mainListViewer);
        listview.setAdapter(adapter);

        /* 검색 값 셋팅 부분 */
        Intent intent = getIntent(); /*데이터 수신*/
        final String keyword = intent.getStringExtra("keyword"); /*String형*/
        arraylist = new ArrayList<>();
        Log.i("ddddd","지금출력되고있다");

        new Thread(new Runnable() {
            public void run() {
                arraylist = new ArrayList<>();
                api = new TripInfoAPI();
                arraylist.addAll(api.getTripInfo(keyword));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < arraylist.size(); i++) {
                            adapter.addItem(arraylist.get(i).getTitle(),arraylist.get(i).getAddress1(),arraylist.get(i).getAddress2(),arraylist.get(i).getTitle());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

}
