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

public class BoardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private static BoardListAdapter adapter = new BoardListAdapter();
    private ArrayList<BoardModel> boardlist = new ArrayList<>();
    private int[] gender = new int[]{R.drawable.img_board_man,
            R.drawable.img_board_woman,
            R.drawable.img_board_all};
    private static String nickname;


    //private BoardListAdapter(){ }

    public void httpwork() {
        removeAllItem();
        HttpBoardList httpBoardDataActivity = new HttpBoardList();
        HttpBoardList.sendTask send = httpBoardDataActivity.new sendTask();
        String result = null;
        try {
            result = send.execute("1").get(); //page번호 전송
            JSONArray jarray = null;
            jarray = new JSONObject(result).getJSONArray("show");

            if (jarray != null) {

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jsonObject = jarray.getJSONObject(i);
                    String boardCode = jsonObject.getString("boardcode");
                    String destination = jsonObject.getString("destination");
                    String nickname = jsonObject.getString("nickname");
                    String date = jsonObject.getString("date");
                    int minage = jsonObject.getInt("minage");
                    int maxage = jsonObject.getInt("maxage");
                    int gender = jsonObject.getInt("gender");
                    String purpose = jsonObject.getString("purpose");


                    BoardModel boardModel = new BoardModel(boardCode, nickname, destination, gender, minage, maxage, purpose, date);
                    boardlist.add(boardModel);
                }

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

    public class BoardListViewHolder extends RecyclerView.ViewHolder {

        public ImageView gender;
        public TextView textnickname;
        public TextView date;
        public TextView destination;
        public TextView minage;
        public TextView maxage;
        public TextView purpose;


        public BoardListViewHolder(View view) {
            super(view);

            gender = view.findViewById(R.id.boardlistitem_imageview_gender);
            textnickname = view.findViewById(R.id.boardlistitem_textview_nickname);
            date = view.findViewById(R.id.boardlistitem_textview_date);
            destination = view.findViewById(R.id.boardlistitem_textview_destination);
            minage = view.findViewById(R.id.boardlistitem_textview_minage);
            maxage = view.findViewById(R.id.boardlistitem_textview_maxage);
            purpose = view.findViewById(R.id.boardlistitem_textview_purpose);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_boardlist, parent, false);
        BoardListViewHolder holder = new BoardListViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        final BoardListViewHolder boardListViewHolder = (BoardListViewHolder) holder;
        final String boardCode = boardlist.get(itemposition).getBoardCode();
        boardListViewHolder.textnickname.setText(boardlist.get(itemposition).getNickname());
        boardListViewHolder.date.setText(boardlist.get(itemposition).getMatchdate());
        boardListViewHolder.destination.setText(boardlist.get(itemposition).getDestination());
        boardListViewHolder.purpose.setText(boardlist.get(itemposition).getPurpose());
        boardListViewHolder.minage.setText(String.valueOf(boardlist.get(itemposition).getMinage()));
        boardListViewHolder.maxage.setText(String.valueOf(boardlist.get(itemposition).getMaxage()));

        if (boardlist.get(itemposition).getGender() == 0)
            boardListViewHolder.gender.setImageResource(gender[0]);
        else if (boardlist.get(itemposition).getGender() == 1)
            boardListViewHolder.gender.setImageResource(gender[1]);
        else
            boardListViewHolder.gender.setImageResource(gender[2]);

        boardListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), BoardViewActivity.class);
                nickname = fragmentBoard.getInstance().getNickname();
                System.out.println("click listener : " + nickname);
                intent.putExtra("nickname", nickname);
                intent.putExtra("boardcode", boardCode);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return boardlist.size(); // RecyclerView의 size return
    }


    void addBoardList(final int position, BoardModel boardModel) {
        boardlist.add(boardModel);
    }

    public void updateData(ArrayList<BoardModel> boardModel) {
        boardlist.clear();
        boardlist.addAll(boardModel);
    }

    public void removeItem(int position) {
        boardlist.remove(position);
    }

    public void removeAllItem() {
        boardlist.clear();
    }
}
