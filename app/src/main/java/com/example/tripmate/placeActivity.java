package com.example.tripmate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

public class placeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_main);

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( "da76283a-f8ad-4e05-ab6b-94612df7b98e" );
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(10);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setCompassMode(true);
        tMapView.setTrackingMode(true);

        linearLayoutTmap.addView( tMapView ); //지도 추가



    }
}
