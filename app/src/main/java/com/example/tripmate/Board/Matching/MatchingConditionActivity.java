package com.example.tripmate.Board.Matching;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.example.tripmate.R;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MatchingConditionActivity extends AppCompatActivity {
    private int startyear;
    private int startmonth;
    private int startday;
    private EditText date_start;
    private EditText time_start;
    private EditText destination;
    private EditText content;
    private EditText age;
    private Button write;
    private RadioButton man;
    private RadioButton woman;
    private RadioButton all;
    private RadioButton carfull;
    private RadioButton food;
    private RadioButton tour;
    private RadioButton picture;
    private Date now;
    private Dialog dialog;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
        Intent intent = getIntent();
        nickname = intent.getExtras().getString("nickname");

        System.out.println("MatchingCondition Activity" + nickname);

        destination = findViewById(R.id.BoardWriteActivity_text_where);
        content = findViewById(R.id.BoardWriteActivity_text_content);
        age = findViewById(R.id.BoardWriteActivity_edittext_age_start);


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
                        MatchingConditionActivity.this,
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
                mTimePicker = new TimePickerDialog(MatchingConditionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_start.setText(selectedHour + "시" + selectedMinute + "분");
                        startDate.set(startyear, startmonth, startday, selectedHour, selectedMinute);
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
                if (TextUtils.isEmpty(destination.getText()) ||
                        TextUtils.isEmpty(content.getText()) ||
                        TextUtils.isEmpty(age.getText()) ||
                        TextUtils.isEmpty(date_start.getText()) ||
                        TextUtils.isEmpty(time_start.getText())) {
                    alert("글 쓰기", "모든칸을 다 채워주세요");
                } else {
                    man = findViewById(R.id.BoardWriteActivity_radio_man);
                    woman = findViewById(R.id.BoardWriteActivity_radio_woman);
                    all = findViewById(R.id.BoardWriteActivity_radio_all);
                    tour = findViewById(R.id.BoardWriteActivity_radio_tour);
                    carfull = findViewById(R.id.BoardWriteActivity_radio_carfull);
                    picture = findViewById(R.id.BoardWriteActivity_radio_picture);
                    food = findViewById(R.id.BoardWriteActivity_radio_food);

                    final String senddestination = destination.getText().toString();
                    String sendgender1 = null;
                    String sendpurpose = null;

                    if (man.isChecked())
                        sendgender1 = "0";
                    else if (woman.isChecked())
                        sendgender1 = "1";
                    else if (all.isChecked())
                        sendgender1 = "2";

                    if (food.isChecked())
                        sendpurpose = "맛집";
                    else if (carfull.isChecked())
                        sendpurpose = "카풀";
                    else if (picture.isChecked())
                        sendpurpose = "사진";
                    else if (tour.isChecked())
                        sendpurpose = "관광";

                    final String sendage = age.getText().toString();


                    final String senddate = date_start.getText().toString();
                    final String sendstarttime = time_start.getText().toString();

                    final String date;
                    String date1;
                    date1 = senddate.replace("년", "-");
                    date1 = date1.replace("월", "-");
                    date1 = date1.replace("일", "");
                    date = date1;


                    String result = null;
                    HttpMatchingCondition httpMatchingCondition = new HttpMatchingCondition();
                    HttpMatchingCondition.sendTask send = httpMatchingCondition.new sendTask();
                    try {
                        result = send.execute(nickname, senddestination, sendgender1, sendage, senddate, sendstarttime, sendpurpose).get();
                        //메시지와 함께 데이터까지 넘어와서 파싱해야함.



                        if ("success".equals(result)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MatchingConditionActivity.this);
                            builder.setTitle("게시판").setMessage("게시글 등록이 완료되었습니다");
                            final String finalDate = date;
                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //final BoardListAdapter adapter = BoardListAdapter.getInstance();
                                  /*  BoardListAdapter adapter = new BoardListAdapter();
                                    adapter.removeAllItem();
                                    adapter.httpwork();*/

                                  onBackPressed();
                                }
                            });
                            dialog = builder.create();
                            dialog.show();

                        } else {
                            alert("게시판", "다시 시도해 주세요");
                        }

                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }


        });


    }

    private void alert(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MatchingConditionActivity.this);
        builder.setTitle(title).setMessage(content);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}

