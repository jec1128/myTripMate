package com.example.tripmate.Plan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tripmate.R;
import java.util.ArrayList;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.PlanListViewHolder> {
    private static PlanListAdapter listAdapter = new PlanListAdapter();
    private ArrayList<PlanListModel> planlist = new ArrayList<>();

    public PlanListAdapter(ArrayList<PlanListModel> planlist){
        this.planlist = planlist;
    }

    public PlanListAdapter() { }

    public void get(){

    }

    @Override
    public PlanListAdapter.PlanListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.plan_card, viewGroup, false);
        PlanListViewHolder holder = new PlanListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PlanListAdapter.PlanListViewHolder holder, int position) {
        holder.txtPlace.setText(planlist.get(position).getPlanPlace());
        holder.txtTitle.setText(planlist.get(position).getPlanTitle());
        holder.txtStart.setText(planlist.get(position).getPlanStart());
        holder.txtEnd.setText(planlist.get(position).getPlanEnd());
    }

    public void addList(int position, PlanListModel model){
        planlist.add(model);
    }

    @Override
    public int getItemCount() {
        return planlist.size();
    }


    public class PlanListViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPlace, txtStart, txtEnd, txtTitle;
        public PlanListViewHolder(View view) {
            super(view);
            txtPlace = (TextView)view.findViewById(R.id.tv_loc);
            txtStart = (TextView)view.findViewById(R.id.tv_startday);
            txtEnd = (TextView)view.findViewById(R.id.tv_endday);
            txtTitle = (TextView)view.findViewById(R.id.tv_title);
        }
    }

}
