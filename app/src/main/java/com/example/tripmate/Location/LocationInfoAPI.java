package com.example.tripmate.Location;

import android.util.Log;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class LocationInfoAPI {

    String serviceKey = "RPU4UbJL2yNOUQMdaXZ10221FLiSFEY3FbuGX4eBbEQQMMTOy6LtUh4auKv4hhgV5OspcFK0QJGwqBI2xEk52A%3D%3D";

    public ArrayList<LocationDataInfo> getLocationInfo(double mapx, double mapy) {

        /* 서비스키는 필요한 경우 아니면 손대지 말 것. */
        /*
         * contentTypeID : 12(관광지), 14(문화시설), 15(축제/공연/행사), 25(여행코스), 28(레포츠), 32(숙박),
         * 38(쇼핑), 39(음식)
         */

        String contentTypeId = "12";

        ArrayList<LocationDataInfo> dto = new ArrayList();

        try {
            String urlStr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="
                    + serviceKey
                    + "&MobileApp=AppTest&MobileOS=ETC&pageNo=1&numOfRows=10&listYN=Y&arrange=A&contentTypeId=&mapX=&mapY=1&radius=2000&contentTypeId="
                    + contentTypeId +"&_type=json";

            URL url = new URL(urlStr);

            Log.i("TDB",urlStr);

            //36.145981
            // 128.393907
            /* 건들이지 않아도 되는 부분 */

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json");

            /* 버퍼에 데이터 담아 데이터 확인하는 부분 */
            BufferedReader br;

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            br.close();
            conn.disconnect();


            /* 파싱하는 부분 */

            Log.i("lgogogogogogo",sb.toString());

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONObject parResponse = (JSONObject) jsonObject.get("response");
            JSONObject parBody = (JSONObject) parResponse.get("body");
            JSONObject parItems = (JSONObject) parBody.get("items");
            JSONArray InfoArray = (JSONArray) parItems.get("item");

            for (int i = 0; i < InfoArray.length(); i++) {
                LocationDataInfo info = new LocationDataInfo();
                JSONObject tripObject = (JSONObject) InfoArray.get(i);

                info.setAddress1(tripObject.getString("address1"));

                if(tripObject.isNull("address2")) {

                } else {

                    info.setAddress2(tripObject.getString("address2"));
                }

                if(tripObject.isNull("firstimage")) {

                } else {
                    info.setFirstimage(tripObject.getString("firstimage"));
                }

                if(tripObject.isNull("firstimage2")) {

                } else {
                    info.setFirstimage(tripObject.getString("firstimage2"));
                }


                if(tripObject.isNull("mapX")) {

                } else {
                    info.setMapX(tripObject.getDouble("mapX"));
                }

                if(tripObject.isNull("mapY")) {

                } else {
                    info.setMapY(tripObject.getDouble("mapY"));
                }

                if(tripObject.isNull("ContentTypeID")) {

                } else {
                    info.setContentTypeID(tripObject.getString("ContentTypeID"));
                }

                if(tripObject.isNull("title")) {

                } else {
                    info.setTitle(tripObject.getString("title"));
                }

                if(tripObject.isNull("dist")) {

                } else {
                    info.setDist(tripObject.getInt("dist"));
                }


                dto.add(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }

}