package com.example.tripmate.Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tripmate.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class PlanTripActivity extends AppCompatActivity {
    private String code, place, title;
    private TextView trip_add, place_title;
    private  ListView listView;
    private TMapView tMapView;
    private ArrayList<String> model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        Intent intent = getIntent();
        code = intent.getStringExtra("plancode");
        place = intent.getStringExtra("place");

        place_title = (TextView)findViewById(R.id.place_title);
        place_title.setText(place);

        LinearLayout linearTmap = (LinearLayout)findViewById(R.id.ln_tmap);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0b480565-acb3-4bcb-a808-20854b70cc21");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setSightVisible(true);
        location();
        linearTmap.addView(tMapView);

        TMapMarkerItem markerItem = new TMapMarkerItem();

        tMapView.setCenterPoint(129.332095, 35.790045);
        TMapPoint tpoint = new TMapPoint(35.790045, 129.332095);
        markerItem.setPosition(0.5f, 1.0f);
        markerItem.setTMapPoint(tpoint);
//          for(int i = 0; i < test.size(); i++){
//            TMapPoint point = new TMapPoint(test.get(i).getX(), test.get(i).getY());
//            markerItem.setPosition(0.5f, 1.0f);
//            markerItem.setTMapPoint(point);
//            tMapView.setCenterPoint(test.get(i).getY(), test.get(i).getX());
//            tMapView.addMarkerItem("marker" + i, markerItem);
//        }
        markerItem.setName("불국사");

        listView = (ListView)findViewById(R.id.lv_trip);

        TripListAdapter listAdapter = new TripListAdapter();
        listView.setAdapter(listAdapter);

        trip_add = (TextView)findViewById(R.id.tv_tripadd);
        trip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = place;
                Intent intent = new Intent(PlanTripActivity.this, PlanKeywordActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });
    }


    //지역별 지도
    private void location(){
        if(place.equals("부산")){
            tMapView.setCenterPoint(129.07556, 35.17944, true);
        }
        else if(place.equals("대구")){
            tMapView.setCenterPoint(128.60250, 35.87222, true);
        }
        else if(place.equals("전주")){
            tMapView.setCenterPoint(127.15000, 35.82500, true);
        }
        else if(place.equals("서울")){
            tMapView.setCenterPoint(126.97806, 37.56667, true);
        }
        else if(place.equals("광주")){
            tMapView.setCenterPoint(127.25639, 37.41750, true);
        }
        else if(place.equals("포항")){
            tMapView.setCenterPoint(129.34167, 36.01944, true);
        }
        else if(place.equals("경주")){
            tMapView.setCenterPoint(129.22472, 35.85611, true);
            //tMapView.setCenterPoint(129.332095, 35.790045, true);
        }
        else if(place.equals("제주")){
            tMapView.setCenterPoint(126.51667, 33.50000, true);
        }
        else if(place.equals("남해")){
            tMapView.setCenterPoint(127.8924234, 34.8376721, true);
        }
        else if(place.equals("통영")){
            tMapView.setCenterPoint(128.42917, 34.85556, true);
        }
        else if(place.equals("속초")){
            tMapView.setCenterPoint(128.59194, 38.20694, true);
        }
        else if(place.equals("춘천")){
            tMapView.setCenterPoint(128.873749, 37.757687, true);
        }
        else if(place.equals("강릉")){
            tMapView.setCenterPoint(128.88333, 37.75000, true);
        }

    }

}
