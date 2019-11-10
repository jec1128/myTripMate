package com.example.tripmate.Board;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;

import java.util.ArrayList;

public class BoardMatchingListAdapter extends RecyclerView.Adapter<BoardMatchingListAdapter.BoardMatchingListViewHolder> {
    private static BoardMatchingListAdapter adapter = new BoardMatchingListAdapter();
    private ArrayList<BoardModel> matchinglist = new ArrayList<>();
    private int[] gender = new int []{R.drawable.img_board_man,
            R.drawable.img_board_woman,
            R.drawable.img_board_all};
    private static String nickname;


    private BoardMatchingListAdapter(){ }

    public void httpwork(){
        removeAllItem();
        HttpBoardMatching httpMatchingActivity = new HttpBoardMatching();
        HttpBoardMatching.sendTask send = httpMatchingActivity.new sendTask();
        send.execute("1"); //page번호 전송
    }

    public static BoardMatchingListAdapter getInstance(){
        if(adapter == null){
            adapter = new BoardMatchingListAdapter();
            return adapter;
        }
        return adapter;
    }

    public class BoardMatchingListViewHolder extends RecyclerView.ViewHolder {

        public ImageView gender;
        public TextView textnickname;
        public TextView date;
        public TextView destination;
        public TextView minage;
        public TextView maxage;
        public TextView thema1;
        public TextView thema2;
        public TextView thema3;


        public BoardMatchingListViewHolder(View view) {
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

    @Override
    public BoardMatchingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boardlist, parent, false);
        BoardMatchingListViewHolder holder = new BoardMatchingListViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(BoardMatchingListViewHolder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        final String boardCode = matchinglist.get(itemposition).getBoardCode();
        holder.textnickname.setText(matchinglist.get(itemposition).getNickname());
        holder.date.setText(matchinglist.get(itemposition).getMatchdate());
        holder.destination.setText(matchinglist.get(itemposition).getDestination());
        holder.thema1.setText(matchinglist.get(itemposition).getThema1());
        if("null".equals(matchinglist.get(itemposition).getThema2()))
            holder.thema2.setText("");
        else
            holder.thema2.setText(matchinglist.get(itemposition).getThema2());

        if("null".equals(matchinglist.get(itemposition).getThema3()))
            holder.thema3.setText("");
        else
            holder.thema3.setText(matchinglist.get(itemposition).getThema3());

        holder.minage.setText(String.valueOf(matchinglist.get(itemposition).getMinage()));
        holder.maxage.setText(String.valueOf(matchinglist.get(itemposition).getMaxage()));

        if(matchinglist.get(itemposition).getGender() == 0)
            holder.gender.setImageResource(gender[0]);
        else if (matchinglist.get(itemposition).getGender() == 1)
            holder.gender.setImageResource(gender[1]);
        else
            holder.gender.setImageResource(gender[2]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), BoardViewActivity.class);
                fragmentBoard fragmentboard = fragmentBoard.getInstance();
                nickname = fragmentboard.getNickname();
                System.out.println("click listener : "+nickname);
                intent.putExtra("nickname", nickname);
                intent.putExtra("boardcode",boardCode);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return matchinglist.size(); // RecyclerView의 size return
    }


    void addBoardList(final int position, BoardModel boardModel){
        matchinglist.add(boardModel);
    }

    public void updateData(ArrayList<BoardModel> boardModel) {
        matchinglist.clear();
        matchinglist.addAll(boardModel);
    }

    public void removeItem(int position) {
        matchinglist.remove(position);
    }

    public void removeAllItem(){
        matchinglist.clear();
    }
}
