package com.example.tripmate;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapView;
import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

public class fragmentActivity5  extends Fragment {
    private TMapGpsManager gpsMarker = null; //mymarker
    private TMapMarkerItem tMarker;
    //마커의 좌표/ 임의의 좌표로 설정해놓음/ 추후에 필요한 값 가져와줘야함
    private TMapPoint tMapPoint = new TMapPoint(37.570841, 126.985302);
    private TMapView tMapView;
    private TMapCircle tMapCircle;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    //프레그먼트에 표시할 뷰를 레이아웃으로 부터 읽어오는역할 onCreateView
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main5, container, false);

        LinearLayout linearLayoutTmap = (LinearLayout) v.findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(getActivity());

        tMapView.setSKTMapApiKey("da76283a-f8ad-4e05-ab6b-94612df7b98e");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(10);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setCompassMode(true);
        tMapView.setTrackingMode(true);

        linearLayoutTmap.addView(tMapView); //지도 추가


        return v;
    }

    private void addTMapMarkerItem(){
        //마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.custom_marker);

        tMarker.setIcon(bitmap); //마커 아이콘 지정
        tMarker.setPosition(0.5f,1.0f); //마커의 중심점을 중앙,하단으로 설정
        tMarker.setTMapPoint(tMapPoint); //마커의 좌표지정
        tMarker.setName("임의의 위치"); //마커의 타이틀 지정
        tMapView.addMarkerItem("tMarKer",tMarker); //지도에 마커추가

        //tMapView.setCenterPoint(126.985302, 37.570841 );

        tMapCircle.setCenterPoint(gpsMarker.getLocation());
        tMapCircle.setAreaColor(Color.GRAY);
        tMapCircle.setAreaAlpha(100);
        tMapCircle.setRadius(2000);
        tMapCircle.setRadiusVisible(true);
        tMapView.addTMapCircle("범위",tMapCircle);



    }





}