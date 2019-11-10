package com.example.tripmate.Board;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

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
import java.net.URL;


public class HttpBoardList extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public class sendTask extends AsyncTask<String, Void, String> {
        public String doInBackground(String... strings) {
            try {
                Ip a = new Ip();
                String ip = a.getIP();
                String url = "http://"+ip+":8080/TripMateServer/Board/ShowList.jsp";
                //String url = "http://192.168.214.146:8080/TripMateServer/Board/ShowList.jsp";

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

                    OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
                    String sendmsg = "pagenumber=" + strings[0];
                    os.write(sendmsg);
                    os.flush();
                    os.close();

                    int retCode = conn.getResponseCode();

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

                    JSONArray jarray = null;
                    jarray = new JSONObject(result).getJSONArray("show");

                    final BoardListAdapter adapter = BoardListAdapter.getInstance();

                    for(int i = 0 ; i < jarray.length(); i++){
                        JSONObject jsonObject = jarray.getJSONObject(i);
                        String boardCode = jsonObject.getString("boardcode");
                        String destination = jsonObject.getString("destination");
                        String nickname = jsonObject.getString("nickname");
                        String date = jsonObject.getString("date");
                        int minage = jsonObject.getInt("minage");
                        int maxage = jsonObject.getInt("maxage");
                        int gender = jsonObject.getInt("gender");
                        String thema1 = jsonObject.getString("thema1");
                        String thema2 = jsonObject.getString("thema2");
                        String thema3 = jsonObject.getString("thema3");

                        BoardModel boardModel = new BoardModel(boardCode,nickname,destination,gender,minage,maxage,thema1,thema2,thema3,date);
                        adapter.addBoardList(i,boardModel);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });



                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }



}
