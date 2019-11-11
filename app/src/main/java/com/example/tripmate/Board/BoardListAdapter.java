package com.example.tripmate.Board;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.BoardListViewHolder> {
    //private static BoardListAdapter adapter = new BoardListAdapter();
    private ArrayList<BoardModel> boardlist = new ArrayList<>();
    private int[] gender = new int []{R.drawable.img_board_man,
            R.drawable.img_board_woman,
            R.drawable.img_board_all};
    private static String nickname;


    //private BoardListAdapter(){ }

    public void httpwork(){
        removeAllItem();
        HttpBoardList httpBoardDataActivity = new HttpBoardList();
        HttpBoardList.sendTask send = httpBoardDataActivity.new sendTask();
        String result = null;
        try {
            result = send.execute("1").get(); //page번호 전송
            JSONArray jarray = null;
            jarray = new JSONObject(result).getJSONArray("show");


            for(int i = 0 ; i < jarray.length(); i++){
                JSONObject jsonObject = jarray.getJSONObject(i);
                String boardCode = jsonObject.getString("boardcode");
                String destination = jsonObject.getString("destination");
                String nickname = jsonObject.getString("nickname");
                String date = jsonObject.getString("date");
                int minage = jsonObject.getInt("minage");
                int maxage = jsonObject.getInt("maxage");
                int gender = jsonObject.getInt("gender");
                String thema1 = jsonObject.getString("thema1");
                String thema2 = jsonObject.getString("thema2");
                String thema3 = jsonObject.getString("thema3");

                BoardModel boardModel = new BoardModel(boardCode,nickname,destination,gender,minage,maxage,thema1,thema2,thema3,date);
                boardlist.add(boardModel);
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

   /* public static BoardListAdapter getInstance(){
        if(adapter == null){
            adapter = new BoardListAdapter();
            return adapter;
        }
        return adapter;
    }*/

    public static class BoardListViewHolder extends RecyclerView.ViewHolder {

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

    @Override
    public BoardListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boardlist, parent, false);
        BoardListViewHolder holder = new BoardListViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(BoardListViewHolder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        final String boardCode = boardlist.get(itemposition).getBoardCode();
        holder.textnickname.setText(boardlist.get(itemposition).getNickname());
        holder.date.setText(boardlist.get(itemposition).getMatchdate());
        holder.destination.setText(boardlist.get(itemposition).getDestination());
        holder.thema1.setText(boardlist.get(itemposition).getThema1());
        if("null".equals(boardlist.get(itemposition).getThema2()))
            holder.thema2.setText("");
        else
            holder.thema2.setText(boardlist.get(itemposition).getThema2());

        if("null".equals(boardlist.get(itemposition).getThema3()))
            holder.thema3.setText("");
        else
            holder.thema3.setText(boardlist.get(itemposition).getThema3());

        holder.minage.setText(String.valueOf(boardlist.get(itemposition).getMinage()));
        holder.maxage.setText(String.valueOf(boardlist.get(itemposition).getMaxage()));

        if(boardlist.get(itemposition).getGender() == 0)
            holder.gender.setImageResource(gender[0]);
        else if (boardlist.get(itemposition).getGender() == 1)
            holder.gender.setImageResource(gender[1]);
        else
            holder.gender.setImageResource(gender[2]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), BoardViewActivity.class);
                nickname = fragmentBoard.getInstance().getNickname();
                System.out.println("click listener : "+nickname);
                intent.putExtra("nickname", nickname);
                intent.putExtra("boardcode",boardCode);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return boardlist.size(); // RecyclerView의 size return
    }


    void addBoardList(final int position, BoardModel boardModel){
        boardlist.add(boardModel);
    }

    public void updateData(ArrayList<BoardModel> boardModel) {
        boardlist.clear();
        boardlist.addAll(boardModel);
    }

    public void removeItem(int position) {
        boardlist.remove(position);
    }

    public void removeAllItem(){
        boardlist.clear();
    }
}
