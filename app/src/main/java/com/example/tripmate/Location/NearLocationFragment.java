package com.example.tripmate.Location;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tripmate.R;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;

import android.content.Intent;

//import com.gun0912.tedpermission.PermissionListener;
//import com.gun0912.tedpermission.TedPermission;

import com.example.tripmate.Location.LocationDataInfo;
import com.example.tripmate.Location.LocationInfoAPI;

import java.util.ArrayList;



public class NearLocationFragment extends Fragment implements TMapGpsManager.onLocationChangedCallback {
    private View v;
    private ViewSwitcher switcher;

    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;

    //지도 화면 좌표 / 임의의 좌표로 설정해놓음/ 추후에 필요한 값 가져와줘야함

    private TMapView tMapView;
    private TMapCircle tMapCircle;
    private TMapPoint tMapPoint;



    private TMapGpsManager gpsMaager = null; //단말기 위치 탐색을 위한 변수(api 클래스)
    private Context mContext =null;

    private LocationInfoAPI api;

    private FloatingActionButton fab;
    private Button searchButton;

    private LocationListViewAdapter adapter;
    private ListView listView;

    private boolean isPerGranted = false;

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
        tMapView.setIconVisibility(false); //현위치 아이콘표시
        tMapView.setZoomLevel(15); //줌레벨
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);

        tMapView.setCompassMode(false); //현재 보는 방향
        tMapView.setTrackingMode(true);

        //gpsMarker = new TMapGpsManager(v.getContext());
        //gpsMarker.setMinTime(1000); //위치가 옮겨질시 갱신 주기
        //gpsMarker.setMinDistance(5); //위치가 옮겨질시 갱신 거리
        //gpsMarker.setProvider(gpsMarker.NETWORK_PROVIDER); //연결된 인터넷으로 현위치를 받음 , 실내일때 유용
        //gpsMarker.setProvider(gpsMarker.GPS_PROVIDER); //GPS로 현위치를 받음

        searchButton= v.findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("search 버튼을 누름");
                addTMapMarkerItem();
            }
        });

        adapter = new LocationListViewAdapter();
        listView = (ListView)v.findViewById(R.id.location_list);
        listView.setAdapter(adapter);

        onSwitchButtonClick1(v); //리스트뷰전환 버튼 클릭이벤트 추가
        onSwitchButtonClick2(v); //맵뷰전환 버튼 클릭이벤트 추가

        onFabClick(v);

        linearLayoutTmap.addView(tMapView); //지도 추가


        return v;
    }




    public void onSwitchButtonClick1(View v) { //리스트뷰전환 버튼 클릭이벤트 추가
        Button switchListButton = (Button) v.findViewById(R.id.switchListButton);
        // Button switchMapButton = (Button)v.findViewById(R.id.switchMapButton);

        switcher = (ViewSwitcher) v.findViewById(R.id.switch_layout);

        if (switchListButton.getText().equals("ListView")) {
            switchListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switcher.showNext();
                }
            });
        }

    }

    public void onSwitchButtonClick2(View v) { //맵뷰전환 버튼 클릭이벤트 추가
        Button switchMapButton = (Button) v.findViewById(R.id.switchMapButton);

        switcher = (ViewSwitcher) v.findViewById(R.id.switch_layout);

        if (switchMapButton.getText().equals("MapView")) {
            switchMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switcher.showPrevious();
                }
            });
        }

    }


    public void onFabClick(View v){
        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPerGranted = true;

                final LocationManager lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }

                Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();



                tMapPoint = new TMapPoint(longitude,latitude);
                tMapPoint.setLatitude(latitude);
                tMapPoint.setLongitude(longitude);


                gpsMaager = new TMapGpsManager(getContext());
                gpsMaager.getLocation().setLatitude(latitude);
                gpsMaager.getLocation().setLongitude(longitude);

                gpsMaager.setMinTime(1000);
                gpsMaager.setMinDistance(5);
                gpsMaager.setProvider(gpsMaager.NETWORK_PROVIDER);
                gpsMaager.OpenGps();

                tMapView.setIconVisibility(true);
                tMapView.setTrackingMode(false);
                tMapView.setSightVisible(false); //시야표출여부를 설정
                tMapView.setCenterPoint(longitude, latitude, true);

                Bitmap bitmap2 = BitmapFactory.decodeResource(v.getContext().getResources(), R.drawable.custom_marker1);
                TMapMarkerItem tMapMarkerItem2 = new TMapMarkerItem(); //지도 마커 표시 변수 (api 클래스)

                tMapMarkerItem2.setIcon(bitmap2); //마커 아이콘 지정

                tMapMarkerItem2.setPosition(0.5f, 0.5f); //마커의 중심점을 중앙,하단으로 설정
                tMapMarkerItem2.setTMapPoint(tMapPoint); //마커의 좌표지정
                tMapMarkerItem2.setName("현재 위치"); //마커의 타이틀 지정
                tMapView.setZoomLevel(13); //줌레벨
                tMapView.setIconVisibility(false); //현위치 아이콘표시
                tMapView.addMarkerItem("tMapMarkerItem2", tMapMarkerItem2); //지도에 마커추가
                tMapView.setCenterPoint(longitude,latitude,true);


                TMapCircle tMapCircle = new TMapCircle();
                tMapCircle.setCenterPoint(tMapPoint);
                tMapCircle.setAreaColor(Color.GRAY);
                tMapCircle.setAreaAlpha(100); //투명도
                tMapCircle.setRadius(10000);
                tMapCircle.setRadiusVisible(true);
                tMapView.addTMapCircle("범위",tMapCircle);


                Toast toast = Toast.makeText(getContext(), "현재 위치 조회완료", Toast.LENGTH_LONG);
                toast.show();


            }
        });


    }

    @Override
    public void onLocationChange(Location location) {
        if (isPerGranted == true) {
            tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        }
    }

    public double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6373.0; //meters
        double dLat = Math.toRadians(lat2) - Math.toRadians(lat1);
        double dLng = Math.toRadians(lon2) - Math.toRadians(lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist; //km로 환산
    }

    public void addTMapMarkerItem() { //마커 추가
        Log.i("ddddd","지금출력되고있다z");

        new Thread(new Runnable() {
            public void run() {
                ArrayList<LocationDataInfo> arrayList = new ArrayList<LocationDataInfo>();
                LocationInfoAPI api = new LocationInfoAPI();


                arrayList.addAll(api.getLocationInfo(tMapPoint.getLongitude(),tMapPoint.getLatitude(),10000));

                for (int i = 0; i < arrayList.size(); i++) {

                    TMapMarkerItem tMapMarkerItem3 = new TMapMarkerItem();//지도 마커 표시 변수 (api 클래스)
                    Bitmap icon = BitmapFactory.decodeResource(v.getContext().getResources(), R.drawable.custom_marker2);

                    tMapMarkerItem3.setTMapPoint(new TMapPoint(arrayList.get(i).getMapY(), arrayList.get(i).getMapX()));

                    tMapMarkerItem3.setIcon(icon); //아이콘 지정

                    tMapMarkerItem3.setName(arrayList.get(i).getTitle());
                    tMapMarkerItem3.setVisible(TMapMarkerItem.VISIBLE);

                    adapter.addItem(arrayList.get(i).getTitle(),  arrayList.get(i).getAddress1(), arrayList.get(i).getFirstimage(), arrayList.get(i).getTel());
                    tMapView.addMarkerItem(arrayList.get(i).getTitle(), tMapMarkerItem3);


                    Log.i("uuuuuu",arrayList.get(i).getTitle());
                    Log.i("uuuuuu",Double.toString(arrayList.get(i).getMapX()));
                    Log.i("uuuuuu",Double.toString(arrayList.get(i).getMapY()));

                }

            }
        }).start();


    }


/*
    public void onSearchButtonClick(View v){
        searchButton= v.findViewById(R.id.SearchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("search 버튼을 누름");
                addTMapMarkerItem();
            }
        });


       }
*/
}








/*
        new Thread(new Runnable() {
            public void run() {
                    arrayList = new ArrayList<>();
                    api = new LocationInfoAPI();

                    arrayList.addAll(api.getLocationInfo(128.3924367,36.1456621));

                            Bitmap bitmap = BitmapFactory.decodeResource(v.getContext().getResources(), R.drawable.custom_marker2);

                            for (int i = 0; i < arrayList.size(); i++) {
                                TMapMarkerItem item = new TMapMarkerItem(); //지도 마커 표시 변수 (api 클래스)

                                    item.setTMapPoint(new TMapPoint(arrayList.get(i).getMapX(), arrayList.get(i).getMapY()));

                                    item.setIcon(bitmap); //아이콘 지정
                                    item.setVisible(TMapMarkerItem.VISIBLE);
                                    item.setName(arrayList.get(i).getTitle());

                                   tMapView.addMarkerItem(String.valueOf(arrayList.get(i).getTitle()), item);
                            }

            }
        }).start();

        */


