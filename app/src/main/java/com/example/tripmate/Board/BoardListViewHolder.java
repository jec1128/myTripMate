package com.example.tripmate.Board;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;

public class BoardListViewHolder extends RecyclerView.ViewHolder{
    public ImageView gender;
    public TextView textnickname;
    public TextView date;
    public TextView destination;
    public TextView minage;
    public TextView maxage;
    public TextView thema1;
    public TextView thema2;
    public TextView thema3;


    public BoardListViewHolder(View view) {
        super(view);

        gender = view.findViewById(R.id.boardlistitem_imageview_gender);
        textnickname = view.findViewById(R.id.boardlistitem_textview_nickname);
        date = view.findViewById(R.id.boardlistitem_textview_date);
        destination = view.findViewById(R.id.boardlistitem_textview_destination);
        minage = view.findViewById(R.id.boardlistitem_textview_minage);
        maxage = view.findViewById(R.id.boardlistitem_textview_maxage);
        thema1 = view.findViewById(R.id.boardlistitem_textview_thema1);
        thema2 = view.findViewById(R.id.boardlistitem_textview_thema2);
        thema3 = view.findViewById(R.id.boardlistitem_textview_thema3);
    }
}
