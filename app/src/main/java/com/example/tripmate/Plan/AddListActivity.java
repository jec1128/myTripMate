package com.example.tripmate.Plan;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.TextView;

import com.example.tripmate.Ip;
import com.example.tripmate.Main.MainSearchListActivity;
import com.example.tripmate.Main.SearchAdapter;
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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class AddListActivity extends AppCompatActivity {
    private EditText place, title;
    private ImageButton start_date, end_date;
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
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlanSearchActivity.class);
                //v.getContext().startActivity(intent);
                startActivityForResult(intent, Code.requestCode);
            }
        });

        Intent intent = getIntent();
        String place1 = intent.getStringExtra("place");

        place.setText(place1);

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

                HttpPlanListAdd httpPlanListAdd = new HttpPlanListAdd();
                HttpPlanListAdd.SendTask sendTask = httpPlanListAdd.new SendTask();
                String result = null;
                try {
                    result = sendTask.execute(sendplace, sendtitle, sendstart, sendend).get();
                    if("success".equals(result)){
                        PlanListAdapter listAdapter = new PlanListAdapter();
                        //listAdapter.removeAllItem();
                        listAdapter.getList();
                        //listAdapter.notifyDataSetChanged();

                        onBackPressed();

                    }
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

    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent){
        if(requestCode == Code.requestCode && resultCode == Code.resultCode)
            place.setText(resultIntent.getStringExtra("place"));
    }

    public static class Code{
        public static int requestCode = 100;
        public static int resultCode = 1;
    }
}
