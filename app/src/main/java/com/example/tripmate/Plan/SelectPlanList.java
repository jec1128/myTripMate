package com.example.tripmate.Plan;

import com.example.tripmate.Plan.DataClassFile.PlanListModel;
import com.example.tripmate.Plan.HttpPlanList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SelectPlanList {
    private ArrayList<PlanListModel> planlist;

    public SelectPlanList() { }
    public ArrayList<PlanListModel> getList(String nickname){
        //removeAllItem();
        HttpPlanList httpPlanList = new HttpPlanList();
        HttpPlanList.sendTask sendTask = httpPlanList.new sendTask();
        String result = null;
        planlist = new ArrayList<>();
        try {
            result = sendTask.execute(nickname).get();
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

            return planlist;

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        //notifyDataSetChanged();
        return planlist;
    }
}
