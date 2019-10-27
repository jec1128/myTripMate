package com.example.tripmate.Board;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.tripmate.R;
import com.example.tripmate.User.HttpUserRegister;
import com.example.tripmate.User.RegisterActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class BoardWriteActivity extends AppCompatActivity {
    private int startyear;
    private int startmonth;
    private int startday;
    private EditText date_start;
    private EditText time_start;
    private EditText time_end;
    private EditText destination;
    private EditText content;
    private EditText age_start;
    private EditText age_end;
    private Button write;
    private RadioGroup gender;
    private RadioButton man;
    private RadioButton woman;
    private RadioButton all;
    private Date now;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
        Intent intent = getIntent();
        String nickname = intent.getExtras().getString("nickname");
        System.out.println("boardwriteactivity" + nickname);
        destination = findViewById(R.id.BoardWriteActivity_text_where);
        content = findViewById(R.id.BoardWriteActivity_text_content);
        age_start = findViewById(R.id.BoardWriteActivity_edittext_age_start);
        age_end = findViewById(R.id.BoardWriteActivity_edittext_age_end);


        final Calendar startDate = Calendar.getInstance();
        date_start = (EditText) findViewById(R.id.BoardWriteActivity_edittext_date_start);
        date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now1 = System.currentTimeMillis();
                now = new Date(now1);

                Calendar defaultDate = Calendar.getInstance();
                Calendar minDate = Calendar.getInstance();
                defaultDate.setTime(now);
                minDate.setTime(now);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BoardWriteActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int printmonth = month + 1;
                                date_start.setText(year + "년" + printmonth + "월" + dayOfMonth + "일");
                                startyear = year;
                                startmonth = month;
                                startday = dayOfMonth;
                                startDate.set(year, month, dayOfMonth);

                            }
                        },
                        defaultDate.get(Calendar.YEAR),
                        defaultDate.get(Calendar.MONTH),
                        defaultDate.get(Calendar.DATE)
                );

                datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());
                datePickerDialog.show();

            }
        });

        final Calendar mcurrentTime = Calendar.getInstance();

        time_start = (EditText) findViewById(R.id.BoardWriteActivity_edittext_time_start);
        time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BoardWriteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_start.setText(selectedHour + "시 " + selectedMinute + "분");
                        startDate.set(startyear, startmonth, startday, selectedHour, selectedMinute);
                        // EditText에 출력할 형식 지정
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        time_end = (EditText) findViewById(R.id.BoardWriteActivity_edittext_time_end);
        time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(BoardWriteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_end.setText(selectedHour + "시 " + selectedMinute + "분");

                        // EditText에 출력할 형식 지정
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });




        write = findViewById(R.id.BoardWriteActivity_button_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(destination.getText()) ||
                        TextUtils.isEmpty(content.getText()) ||
                        TextUtils.isEmpty(age_start.getText()) ||
                        TextUtils.isEmpty(age_end.getText()) ||
                        TextUtils.isEmpty(date_start.getText()) ||
                        TextUtils.isEmpty(time_start.getText()) ||
                        TextUtils.isEmpty(time_end.getText())) {
                    alert("글 쓰기", "모든칸을 다 채워주세요");
                }
                    else{
                        int minage = Integer.parseInt(age_start.getText().toString());
                        int maxage = Integer.parseInt(age_end.getText().toString());
                        if (minage > maxage){
                            alert("글 쓰기", "최소나이가 최대나이보다 클 수 없습니다");
                        }
                        else{
                            CheckBox cb1 = findViewById(R.id.BoardWriteActivity_check_food);
                            CheckBox cb2 = findViewById(R.id.BoardWriteActivity_check_rest);
                            CheckBox cb3 = findViewById(R.id.BoardWriteActivity_check_nature);
                            CheckBox cb4 = findViewById(R.id.BoardWriteActivity_check_leisure);
                            CheckBox cb5 = findViewById(R.id.BoardWriteActivity_check_walk);
                            CheckBox cb6 = findViewById(R.id.BoardWriteActivity_check_biking);

                            int checkCount = 0;

                            if (cb1.isChecked()) {
                                checkCount++;

                            }
                            if (cb2.isChecked()) {
                                checkCount++;

                            }
                            if (cb3.isChecked()) {
                                checkCount++;

                            }
                            if (cb4.isChecked()) {
                                checkCount++;

                            }
                            if (cb5.isChecked()) {
                                checkCount++;
                            }
                            if (cb6.isChecked()) {
                                checkCount++;
                            }
                            ArrayList<String> interest = new ArrayList<>(3);
                            if (checkCount > 3 || checkCount == 0) {
                                alert("체크 갯수 제한","1,2,3개만 입력하세요");
                            } else{
                                man = findViewById(R.id.RegisterActivity_radio_man);
                                woman = findViewById(R.id.RegisterActivity_radio_woman);

                                String senddestination = destination.getText().toString();

                                String sendcontent = content.getText().toString();
                                String sendgender = null;
                                if (man.isChecked())
                                    sendgender = "0";
                                else if (woman.isChecked())
                                    sendgender = "1";
                                else if (all.isChecked())
                                    sendgender = "2";
                                String sendminage = age_start.getText().toString();
                                String sendmaxage=age_end.getText().toString();

                                String senddate = date_start.getText().toString();
                                String sendstarttime= time_start.getText().toString();
                                String sendendtime = time_end.getText().toString();

                                if (cb1.isChecked() == true) interest.add(cb1.getText().toString());
                                if (cb2.isChecked() == true) interest.add(cb2.getText().toString());
                                if (cb3.isChecked() == true) interest.add(cb3.getText().toString());
                                if (cb4.isChecked() == true) interest.add(cb4.getText().toString());
                                if (cb5.isChecked() == true) interest.add(cb5.getText().toString());
                                if (cb6.isChecked() == true) interest.add(cb6.getText().toString());
                                String result = null;

                                HttpUserRegister httpUserDataActivity = new HttpUserRegister();
                                HttpUserRegister.sendTask send = httpUserDataActivity.new sendTask();
                                try {
                                    if(interest.size() == 1)
                                        result = send.execute(senddestination, sendcontent, sendgender, sendminage, sendmaxage, senddate,sendstarttime,sendendtime, interest.get(0)," "," ").get();
                                    else if (interest.size() == 2)
                                        result = send.execute(senddestination, sendcontent, sendgender, sendminage, sendmaxage, senddate,sendstarttime,sendendtime, interest.get(0),interest.get(1)," ").get();
                                    else if (interest.size() == 3)
                                        result = send.execute(senddestination, sendcontent, sendgender, sendminage, sendmaxage, senddate,sendstarttime,sendendtime, interest.get(0),interest.get(1),interest.get(2)).get();

                                    if("success".equals(result)){

                                    }
                                } catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                }

            }
        });



    }
    private void alert(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BoardWriteActivity.this);
        builder.setTitle(title).setMessage(content);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}