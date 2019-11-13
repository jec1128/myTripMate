package com.example.tripmate.Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tripmate.R;
import com.example.tripmate.fragmentActivity2;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

public class PlanTripActivity extends AppCompatActivity {
    private String code;
    private TextView trip_add;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_trip);

        Intent intent = getIntent();
        code = intent.getExtras().getString("plancode");

        LinearLayout linearTmap = (LinearLayout)findViewById(R.id.ln_tmap);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("0b480565-acb3-4bcb-a808-20854b70cc21");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        linearTmap.addView(tMapView);

        TMapMarkerItem markerItem = new TMapMarkerItem();
        //TMapPoint tpoint = new TMapPoint(mapY, mapX);
        //markerItem.setTMapPoint(tpoint);
        markerItem.setVisible(TMapMarkerItem.VISIBLE);
        //markerItem.setIcon();

        trip_add = (TextView)findViewById(R.id.tv_tripadd);
        trip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PlanTripActivity.this);
                builder.setTitle("test").setMessage("test");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }


}
