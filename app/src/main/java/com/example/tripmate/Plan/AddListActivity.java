package com.example.tripmate.Plan;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tripmate.Ip;
import com.example.tripmate.R;
import com.example.tripmate.fragmentActivity2;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class AddListActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private EditText place, title;
    private ImageButton add_close, start_date, end_date;
    private TextView text_sDate, text_eDate;
    private Button btOk;
    Calendar calendar = Calendar.getInstance();

    //시작일
    DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay();
        }
    };
    //종료일
    DatePickerDialog.OnDateSetListener datePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay2();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_addplanlist);

        //여행지
        place = (EditText) findViewById(R.id.et_place);

        //일정타이틀
        title = findViewById(R.id.et_title);

        //일정시작일
        text_sDate = findViewById(R.id.tv_stardate);
        start_date = findViewById(R.id.imgbt_startdate);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddListActivity.this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //일정종료일
        text_eDate = findViewById(R.id.tv_enddate);
        end_date = findViewById(R.id.imgbt_enddate);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddListActivity.this, datePicker2, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btOk = findViewById(R.id.bt_ok);
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendplace = place.getText().toString();
                String sendtitle = title.getText().toString();
                String sendstart = text_sDate.getText().toString();
                String sendend = text_eDate.getText().toString();

                //String result = null;
                try {
                    SendTask send = new SendTask();
                    send.execute(sendplace, sendtitle, sendstart, sendend).get();


                    onBackPressed();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //시작일
    private void updateDisplay(){
        String format = "yy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        String a = simpleDateFormat.format(calendar.getTime());
        text_sDate.setText(a);
    }
    //종료일
    private void updateDisplay2(){
        String format = "yy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        String a = simpleDateFormat.format(calendar.getTime());
        text_eDate.setText(a);
    }

    //데이터 송수신
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer buffer = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                        buffer.append(' ');
                    }
                    reader.close();

                    String result = buffer.toString();
                    System.out.println(result);

                    JSONArray jarray = null;
                    jarray = new JSONObject(result).getJSONArray("addList");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject jsonObject = jarray.getJSONObject(i);
                        String planCode = jsonObject.getString("plancode");
                        String place = jsonObject.getString("place");
                        String title = jsonObject.getString("title");
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        PlanListModel planListModel = new PlanListModel(planCode, place, title, start, end);
                        System.out.println(planListModel);
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
