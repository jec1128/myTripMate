package com.example.tripmate.Plan.Cost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tripmate.Plan.SelectPlanList;
import com.example.tripmate.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class PlanCostActivity extends AppCompatActivity {
    public static String code, start, end;
    private static RecyclerView recyclerView, recyclerView2;
    private static PlanCostActivity instance;
    private String place;
    private TextView place_title, total_price;
    private ImageButton back, costAdd;
    private PlanCostAdapter planCostAdapter;
    private ArrayList<String> dateList;
    private SectionedRecyclerViewAdapter sectionAdapter;

    public static PlanCostActivity getInstance() {
        if (instance == null) {
            instance = new PlanCostActivity();
            return instance;
        }
        return instance;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_cost);

        Intent intent = getIntent();
        code = intent.getStringExtra("plancode");
        place = intent.getStringExtra("place");
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");

        recyclerView = findViewById(R.id.rc_cost1);
        init();

        getDate(start, end);
        init2();

        place_title = findViewById(R.id.cost_title);
        place_title.setText(place);

        costAdd = findViewById(R.id.trip_cost_add);
        costAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PlanCostActivity.this, CostAddActivity1.class);
                intent1.putExtra("plancode", code);
                startActivity(intent1);
            }
        });

        back = findViewById(R.id.cost_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SelectPlanList data = new SelectPlanList();
        planCostAdapter = new PlanCostAdapter(data.getCostlist1(getPlanCode()));
        recyclerView.setAdapter(planCostAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //날짜 계산해서 가져오기 시작날짜-마지막날짜사이의 날짜들이 나옴
    public ArrayList<String> getDate(String start, String end) {
        final String DATE_PATTERN = "yy/MM/dd";
        String inputStartDate = start;
        String inputEndDate = end;
        dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        try {
            Date startDate = sdf.parse(inputStartDate);
            Date endDate = sdf.parse(inputEndDate);

            Date currentDate = startDate;

            while (currentDate.compareTo(endDate) <= 0) {
                dateList.add(sdf.format(currentDate));
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                currentDate = c.getTime();
            }

            for (String date : dateList) {
                Log.i("dateView", date);
            }
            return dateList;
        } catch (Exception e) {
        }
        return dateList;
    }

//    public void onHeaderRootViewClicked(@NonNull CostSectionAdapter section){
//        SelectPlanList data = new SelectPlanList();
//        String date = CostSectionAdapter.title;
//        Intent intent = new Intent(this, CostAddActivity2.class);
//        intent.putExtra("plancode", code);
//        intent.putExtra("date",date);
//        startActivity(intent);
//        section.addItem(data.getCostlist(getPlanCode()));
//    }

    public void init2() {
        SelectPlanList data = new SelectPlanList();
        ArrayList<CostModel> list = new ArrayList<>();
        list = data.getCostlist2(getPlanCode());
        sectionAdapter = new SectionedRecyclerViewAdapter();
        for (int i = 0; i < dateList.size(); i++) {
            sectionAdapter.addSection(new CostSectionAdapter(dateList.get(i)));
            for (int j = 0; j < list.size(); j++) {
                System.out.println("ㄹ너ㅏㅓㅣㄴ더ㅏㅓㅈㄹㄷ " + dateList.get(i));
                System.out.println("dfjsleijfisejf " + list.get(j).getCostDate());
                if (dateList.get(i).equals(list.get(j).getCostDate())) {
                    CostSectionAdapter.addItem(list);
                    System.out.println("같ㅇ늠ㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
                } else {
                    System.out.println("다름ㅁㅁㅁㅁㅁㅁ");
                }
            }
        }
        recyclerView2 = findViewById(R.id.rc_cost2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(sectionAdapter);

    }

    public String getPlanCode() {
//        Intent intent = getIntent();
//        code = intent.getStringExtra("plancode");
        return code;
    }
}
