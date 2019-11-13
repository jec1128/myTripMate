package com.example.tripmate.Plan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tripmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.PlanListViewHolder> {
    private static PlanListAdapter listAdapter;
    private ArrayList<PlanListModel> planlist = new ArrayList<>();
    private Context context;

    public PlanListAdapter(Context context, ArrayList<PlanListModel> planlist){
        this.context = context;
        this.planlist = planlist;
    }

    public PlanListAdapter() { }

    //일정목록 가져오기
    public void getList(){
        //removeAllItem();
        HttpPlanList httpPlanList = new HttpPlanList();
        HttpPlanList.sendTask sendTask = httpPlanList.new sendTask();
        String result = null;
        try {
            result = sendTask.execute("1").get(); //page번호 전송
            JSONArray jarray = null;
            jarray = new JSONObject(result).getJSONArray("addlist");

            if (jarray != null) {
                for(int i = 0; i < jarray.length(); i++) {
                    JSONObject jsonObject = jarray.getJSONObject(i);
                    String code = jsonObject.getString("plancode");
                    String place = jsonObject.getString("place");
                    String title = jsonObject.getString("title");
                    String start = jsonObject.getString("start");
                    String end = jsonObject.getString("end");
                    PlanListModel model = new PlanListModel(code, place, title, start, end);
                    planlist.add(model);
                }
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        //notifyDataSetChanged();
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
        int cardposit = position;
        final PlanListViewHolder planListViewHolder = (PlanListViewHolder) holder;
        final String code = planlist.get(cardposit).getPlanCode();
        planListViewHolder.txtPlace.setText(planlist.get(cardposit).getPlanPlace());
        planListViewHolder.txtTitle.setText(planlist.get(cardposit).getPlanTitle());
        planListViewHolder.txtStart.setText(planlist.get(cardposit).getPlanStart());
        planListViewHolder.txtEnd.setText(planlist.get(cardposit).getPlanEnd());

        planListViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlanTripActivity.class);
                intent.putExtra("plancode", code);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void addList(ArrayList <PlanListModel> planlist){
        this.planlist.addAll(planlist);
    }

    public void removeAllItem() {
        planlist.clear();
    }

    @Override
    public int getItemCount() {
        return planlist.size();
    }


    public class PlanListViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPlace, txtStart, txtEnd, txtTitle;
        public ImageButton imageButton;
        public PlanListViewHolder(View view) {
            super(view);
            txtPlace = (TextView)view.findViewById(R.id.tv_loc);
            txtStart = (TextView)view.findViewById(R.id.tv_startday);
            txtEnd = (TextView)view.findViewById(R.id.tv_endday);
            txtTitle = (TextView)view.findViewById(R.id.tv_title);
            imageButton = (ImageButton)view.findViewById(R.id.img_tripshow);

        }
    }

}
