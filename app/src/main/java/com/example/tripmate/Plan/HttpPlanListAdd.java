package com.example.tripmate.Plan;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.tripmate.Ip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpPlanListAdd extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    class SendTask extends AsyncTask<String, Void, String> {
        public String doInBackground(String... strings) {
            try {
                Ip a = new Ip();
                String ip = a.getIP();
                String url = "http://" + ip + ":8080/TripMateServer/Plan/AddOk.jsp";
                URL obj = null;
                try {
                    obj = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

                    conn.setReadTimeout(3000);
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Accept-Charset", "UTF-8");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


                    OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                    String sendMsg = "place=" + strings[0] + "&title=" + strings[1] + "&start=" + strings[2] + "&end=" + strings[3];
                    System.out.println(sendMsg);
                    osw.write(sendMsg);
                    osw.flush();
                    osw.close();


                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                        response.append(' ');
                    }
                    br.close();

                    String result = response.toString();
                    System.out.println(result);
                    //final PlanListAdapter listAdapter = PlanListAdapter.getInstance();

                    JSONArray jarray = null;
                    jarray = new JSONObject(result).getJSONArray("addList");


                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jsonObject = jarray.getJSONObject(i);
                        String planCode = jsonObject.getString("plancode");
                        String place = jsonObject.getString("place");
                        String title = jsonObject.getString("title");
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        //PlanListModel planListModel = new PlanListModel(planCode, place, title, start, end);
                        //System.out.println(planListModel);
                        //listAdapter.addList(i, planListModel);
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
        }
    }

}
