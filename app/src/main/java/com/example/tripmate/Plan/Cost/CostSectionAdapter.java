package com.example.tripmate.Plan.Cost;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.tripmate.R;

import java.util.ArrayList;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class CostSectionAdapter extends Section {
    private static final String TAG = CostSectionAdapter.class.getSimpleName();
    private static ArrayList<CostModel> costlist = new ArrayList<>();
    public String title;
    //private final ClickListener clickListener;
    private int[] costImg = new int[]{
            R.drawable.ic_cost_category1, //숙박
            R.drawable.ic_cost_category2, //교통
            R.drawable.ic_cost_category3, //관광
            R.drawable.ic_cost_category4, //쇼핑
            R.drawable.ic_cost_category5, //식비
            R.drawable.ic_cost_category6}; //기타

    public CostSectionAdapter(String title) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.trip_cost_item)
                .headerResourceId(R.layout.trip_cost_header)
                .build());
        this.title = title;
        //this.clickListener = clickListener;
        //this.costlist = costlist;
    }

    public static void addItem(ArrayList<CostModel> list) {
        costlist = list;
        System.out.println("들어왔돠돠돠돠 ");
    }

    @Override
    public int getContentItemsTotal() {
        return costlist.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("32432423423", "000000000000000000");
        ItemViewHolder iHolder = (ItemViewHolder) holder;
        final int pos = position;
        if (title.equals(costlist.get(pos).getCostDate())) {
            if (costlist.get(pos).getCostCategory() == 0) {
                iHolder.costImg.setImageResource(costImg[0]);
            } else if (costlist.get(pos).getCostCategory() == 1) {
                iHolder.costImg.setImageResource(costImg[1]);
            } else if (costlist.get(pos).getCostCategory() == 2) {
                iHolder.costImg.setImageResource(costImg[2]);
            } else if (costlist.get(pos).getCostCategory() == 3) {
                iHolder.costImg.setImageResource(costImg[3]);
            } else if (costlist.get(pos).getCostCategory() == 4) {
                iHolder.costImg.setImageResource(costImg[4]);
            } else {
                iHolder.costImg.setImageResource(costImg[5]);
            }
            iHolder.costTitle.setText(costlist.get(pos).getCostTitle());
            System.out.println("돈돈ㄷ녿녿" + costlist.get(pos).getCostPrice());
            iHolder.costPrice.setText(Integer.toString(costlist.get(pos).getCostPrice()));
            if (costlist.get(pos).getCostType() == 0) {
                iHolder.costType.setText("현금");
            } else if (costlist.get(pos).getCostType() == 1) {
                iHolder.costType.setText("카드");
            }
            Log.i("32432423423", "11111111111");
        } else if (!title.equals(costlist.get(pos).getCostDate())) {

            Log.i("32432423423", "222222222222");
        }
        Log.i("32432423423", "333333333333");

        //수정
        iHolder.itemView.setOnClickListener(new View.OnClickListener() {
            final String costCode = costlist.get(pos).getCostCode();
            final String planCode = costlist.get(pos).getPlancode();
            final String costDate = costlist.get(pos).getCostDate();
            final String costTitle = costlist.get(pos).getCostTitle();
            final int costPrice = costlist.get(pos).getCostPrice();

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CostUpdateActivity.class);
                intent.putExtra("costcode", costCode);
                intent.putExtra("plancode", planCode);
                intent.putExtra("date", costDate);
                intent.putExtra("title", costTitle);
                intent.putExtra("price", costPrice);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder hHolder = (HeaderViewHolder) holder;
        hHolder.cost_date.setText(title);
//        StringTokenizer token1 = new StringTokenizer(title, "/");
//        String[] temp = new String[3];
//        int count = 0;
//        while (token1.hasMoreTokens()) {
//            temp[count++] = token1.nextToken();
//        }
//
//        for(int i = 0; i < temp.length; i++) {
//            Log.i("32432423423",temp[i]);
//        }
//
//        hHolder.cost_wrap_yy.setText(temp[0]);
//        hHolder.cost_wrap_mm.setText(temp[1]);
//        hHolder.cost_wrap_nn.setText(temp[2]);

//        hHolder.cost_add.setOnClickListener(v ->
//                clickListener.onHeaderRootViewClicked(title, this)
//        );
        hHolder.cost_add.setOnClickListener(new View.OnClickListener() {
            final String code = PlanCostActivity.code;
            final String start = PlanCostActivity.start;
            final String end = PlanCostActivity.end;

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CostAddActivity2.class);
                intent.putExtra("plancode", code);
                intent.putExtra("start", start);
                intent.putExtra("end", end);
                System.out.println("코드넘어왔뉘위위우 " + code);
                intent.putExtra("date", title);
                v.getContext().startActivity(intent);
            }
        });
    }

//    public interface ClickListener {
//        void onHeaderRootViewClicked(String title, @NonNull final CostSectionAdapter section);
//    }
}

