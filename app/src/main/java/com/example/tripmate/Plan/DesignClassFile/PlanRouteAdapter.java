package com.example.tripmate.Plan.DesignClassFile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.tripmate.Plan.DataClassFile.PlanRouteDataModel;
import com.example.tripmate.R;

import java.util.ArrayList;
import java.util.StringTokenizer;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class PlanRouteAdapter extends Section {

    private static ArrayList<PlanRouteDataModel> mItems = new ArrayList<>();
    private final String date;

    private final ClickListener clickListener;

    public PlanRouteAdapter(String date,@NonNull final ClickListener clickListener) {
        // call constructor with layout resources for this Section header and items
        super(SectionParameters.builder()
                .itemResourceId(R.layout.activity_plan_trip_item)
                .headerResourceId(R.layout.plan_trip_header)
                .build());
        this.date = date;
        this.clickListener = clickListener;
        // this.mItems.add(data);
    }

    @Override
    public int getContentItemsTotal() {
        return mItems.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {
        // return a custom instance of ViewHolder for the items of this section

        return new PlanRouteViewHolder(view);
    }

    public static void addlist(ArrayList<PlanRouteDataModel> list) {
        mItems = list;
        System.out.println("들어왔돠돠돠돠 ");
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        // return an empty instance of ViewHolder for the headers of this section
        return new PlanRouteHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final PlanRouteHeaderViewHolder headerHolder = (PlanRouteHeaderViewHolder) holder;

        StringTokenizer token1 = new StringTokenizer(date, "/");
        String[] temp = new String[3];
        int count = 0;
        while (token1.hasMoreTokens()) {
            temp[count++] = token1.nextToken();
        }

        for(int i = 0; i < temp.length; i++) {
            Log.i("32432423423",temp[i]);
        }

        headerHolder.trip_wrap_yy.setText(temp[0]);
        headerHolder.trip_wrap_mm.setText(temp[1]);
        headerHolder.trip_wrap_nn.setText(temp[2]);

        headerHolder.trip_btn.setOnClickListener(v ->
                clickListener.onHeaderRootViewClicked(date,this)
        );
    }

    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Log.i("32432423423", "000000000000000000");
        PlanRouteViewHolder itemHolder = (PlanRouteViewHolder) holder;
        Log.i("32432423423", mItems.get(0).getTitle());
        String mapX = Double.toString(mItems.get(position).getLocationx());
        String mapY = Double.toString(mItems.get(position).getLocationy());
        // bind your view here
        String temp = Integer.toString(mItems.get(position).getIndex());
        String temp2 = Integer.toString(mItems.get(position).getTotalPrice());
        itemHolder.trip_item_numbering.setText(temp);
        itemHolder.trip_item_route.setText(mItems.get(position).getTitle());
        itemHolder.trip_item_price.setText(temp2);
        itemHolder.trip_item_memo.setText(mItems.get(position).getMemo());
        Log.i("32432423423", "222222222222");
    }

    public void addItem(PlanRouteDataModel item) {
        mItems.add(item);
    }

    public interface ClickListener {
        void onHeaderRootViewClicked(@NonNull final String sectionTitle, @NonNull final PlanRouteAdapter section);
    }
}