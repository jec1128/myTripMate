package com.example.tripmate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import com.skt.Tmap.TMapView;
import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;

public class NearLocationFragment extends Fragment{
   /* private View v;
    private ViewSwitcher switcher;

    private TMapGpsManager gpsMarker = null; //mymarker
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
        v = inflater.inflate(R.layout.near_loaction_fragment, container, false);

        RelativeLayout linearLayoutTmap = (RelativeLayout) v.findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(getActivity());


        tMapView.setSKTMapApiKey("da76283a-f8ad-4e05-ab6b-94612df7b98e");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(10);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setCompassMode(true);
        tMapView.setTrackingMode(true);

        addTMapMarkerItem(); //마커 추가
        onSwitchButtonClick1(v); //리스트뷰전환 버튼 클릭이벤트 추가
        onSwitchButtonClick2(v); //맵뷰전환 버튼 클릭이벤트 추가
        linearLayoutTmap.addView(tMapView); //지도 추가



        return v;
    }

   public void addTMapMarkerItem(){ //마커 추가
        //마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(v.getContext().getResources(),R.drawable.custom_marker1);
        TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();

        tMapMarkerItem.setIcon(bitmap); //마커 아이콘 지정
        tMapMarkerItem.setPosition(0.5f,1.0f); //마커의 중심점을 중앙,하단으로 설정
        tMapMarkerItem.setTMapPoint(tMapPoint); //마커의 좌표지정
        tMapMarkerItem.setName("임의의 위치"); //마커의 타이틀 지정
        tMapView.addMarkerItem("tMapMarkerItem",tMapMarkerItem); //지도에 마커추가

       *//* tMapView.setCenterPoint(126.985302, 37.570841 );

        tMapCircle.setCenterPoint(gpsMarker.getLocation());
        tMapCircle.setAreaColor(Color.GRAY);
        tMapCircle.setAreaAlpha(100);
        tMapCircle.setRadius(2000);
        tMapCircle.setRadiusVisible(true);
        tMapView.addTMapCircle("범위",tMapCircle);*//*
    }

    public void onSwitchButtonClick1(View v){ //리스트뷰전환 버튼 클릭이벤트 추가
        Button switchListButton = (Button) v.findViewById(R.id.switchListButton);
       // Button switchMapButton = (Button)v.findViewById(R.id.switchMapButton);

        switcher = (ViewSwitcher) v.findViewById(R.id.switch_layout);

        if(switchListButton.getText().equals("ListView"))
        {
            switchListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switcher.showNext();
                }
            });
        }

    }

    public void onSwitchButtonClick2(View v){ //맵뷰전환 버튼 클릭이벤트 추가
        Button switchMapButton = (Button)v.findViewById(R.id.switchMapButton);

        switcher = (ViewSwitcher) v.findViewById(R.id.switch_layout);

        if(switchMapButton.getText().equals("MapView"))
        {
            switchMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switcher.showPrevious();
                }
            });
        }


    }
    */


}