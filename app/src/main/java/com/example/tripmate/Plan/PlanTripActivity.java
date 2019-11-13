package com.example.tripmate.Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tripmate.Main.MainKeywordActivity;
import com.example.tripmate.Main.MainSearchListActivity;
import com.example.tripmate.R;
import com.example.tripmate.fragmentActivity2;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

public class PlanTripActivity extends AppCompatActivity {
    private String code, place;
    private TextView trip_add, place_title;
    private TMapView tMapView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        Intent intent = getIntent();
        code = intent.getStringExtra("plancode");
        place = intent.getStringExtra("place");

        place_title.setText(place);

        LinearLayout linearTmap = (LinearLayout)findViewById(R.id.ln_tmap);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0b480565-acb3-4bcb-a808-20854b70cc21");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        tMapView.setCenterPoint(126.874237, 33.431441);
        linearTmap.addView(tMapView);

        trip_add = (TextView)findViewById(R.id.tv_tripadd);
        trip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = place;
                Intent intent = new Intent(PlanTripActivity.this, MainKeywordActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });
    }

    private void addMarker(){
        TMapMarkerItem markerItem = new TMapMarkerItem();
        //TMapPoint tpoint = new TMapPoint(mapY, mapX);
        //markerItem.setTMapPoint(tpoint);
        markerItem.setVisible(TMapMarkerItem.VISIBLE);
        //markerItem.setIcon();
    }

}
