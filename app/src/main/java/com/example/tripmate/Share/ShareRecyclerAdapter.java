package com.example.tripmate.Share;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ShareRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<TripRouteDataInfo> tripRoute = null;

    public ShareRecyclerAdapter(ArrayList<TripRouteDataInfo> dataList)
    {
        tripRoute = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.share_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.share_title.setText(tripRoute.get(position).getTripTitle());
        viewHolder.share_location_title.setText(tripRoute.get(position).getTripLocation());
        viewHolder.share_content.setText(tripRoute.get(position).getTripDescribe());
    }

    @Override
    public int getItemCount() {
        String a = String.valueOf(tripRoute.size());
        Log.i("TDB", a);
        return tripRoute.size();
    }
}

