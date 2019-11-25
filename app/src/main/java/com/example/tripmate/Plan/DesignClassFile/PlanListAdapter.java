package com.example.tripmate.Plan.DesignClassFile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tripmate.Plan.DataClassFile.PlanListModel;
import com.example.tripmate.Plan.HttpPlanList;
import com.example.tripmate.Plan.PlanTripActivity;
import com.example.tripmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListViewHolder>  {
    private ArrayList<PlanListModel> planlist;

    public PlanListAdapter() {

    }
    public PlanListAdapter(ArrayList<PlanListModel> dataList)
    {
        this.planlist = dataList;
    }
    @NonNull
    @Override
    public PlanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.plan_card, parent, false);
        PlanListViewHolder viewHolder = new PlanListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanListViewHolder planListViewHolder, int position) {
        final int pos = position;
        planListViewHolder.txtPlace.setText(planlist.get(pos).getPlanPlace());
        planListViewHolder.txtTitle.setText(planlist.get(pos).getPlanTitle());
        planListViewHolder.txtStart.setText(planlist.get(pos).getPlanStart());
        planListViewHolder.txtEnd.setText(planlist.get(pos).getPlanEnd());

        planListViewHolder.viewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = planlist.get(pos).getPlanCode();
                final String place = planlist.get(pos).getPlanPlace();
                final String start = planlist.get(pos).getPlanStart();
                final String end = planlist.get(pos).getPlanEnd();

                Intent intent = new Intent(v.getContext(), PlanTripActivity.class);
                intent.putExtra("plancode", code);
                intent.putExtra("place", place);
                intent.putExtra("startDate", start);
                intent.putExtra("endDate", end);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return planlist.size();
    }


}
