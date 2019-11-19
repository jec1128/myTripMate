/* 내가 수정할 부분 */
/* 보드 리스트를 보여주는 부분 */

package com.example.tripmate.Board;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import com.example.tripmate.SaveSharedPreference;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tripmate.Board.Matching.MatchingConditionActivity;
import com.example.tripmate.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class fragmentBoard extends Fragment implements SwipyRefreshLayout.OnRefreshListener {
    private static fragmentBoard instance;
    private View boardFragment;
    private FloatingActionButton write;
    private FloatingActionButton matching;
    private static String nickname;
    private static RecyclerView recyclerView;
    private static BoardListAdapter adapter;
    private static ArrayList<BoardModel> dataList;
    private static int refreshCount = 1;
    private SwipyRefreshLayout swipeRefreshLayout;
    @SuppressLint("ValidFragment")
    private fragmentBoard() { }

    public static fragmentBoard getInstance() {
        if (instance == null) {
            instance = new fragmentBoard();
            return instance;
        }
        return instance;
    }

    public static void setRefreshCount(int refreshCount) {
        fragmentBoard.refreshCount = refreshCount;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boardFragment = inflater.inflate(R.layout.fragment_board, container, false);
        swipeRefreshLayout = (SwipyRefreshLayout) boardFragment.findViewById(R.id.boardfragment_swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = boardFragment.findViewById(R.id.boardfragment_recyclerview);
        dataList = new ArrayList<>();
        init();
        //이전 화면에서 닉네임 얻어오기
        Bundle extra = this.getArguments();

       /* if (extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
            System.out.println("fragment board : " + nickname);
        }*/

        nickname = SaveSharedPreference.getNickName(this.getActivity()).toString();

        write = boardFragment.findViewById(R.id.boardfragment_button_write);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);

            }
        });

        matching = boardFragment.findViewById(R.id.boardfragment_button_matching);
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MatchingConditionActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);

            }
        });

        return boardFragment;
    }

    public String getNickname() {
        return nickname;
    }

    public void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new BoardListAdapter(jsonParserForGetBoardlist());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }





    //제이슨 파싱해서 데이터 리스트 얻어오는 부분
    private ArrayList<BoardModel> jsonParserForGetBoardlist() {

        HttpBoardList httpBoardDataActivity = new HttpBoardList();
        HttpBoardList.sendTask send = httpBoardDataActivity.new sendTask();

        String result = null;


        try {
            String sendCount = Integer.toString(refreshCount);
            result = send.execute(sendCount).get(); //page번호 전송
            JSONArray jarray = null;
            refreshCount++;
            jarray = new JSONObject(result).getJSONArray("show");

            if (jarray != null) {

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jsonObject = jarray.getJSONObject(i);

                    String date = jsonObject.getString("date");
                    int gender = jsonObject.getInt("gender");
                    int maxage = jsonObject.getInt("maxage");
                    String purpose = jsonObject.getString("purpose");
                    int minage = jsonObject.getInt("minage");
                    String nickname = jsonObject.getString("nickname");
                    String destination = jsonObject.getString("destination");
                    String boardCode = jsonObject.getString("boardcode");

                    BoardModel boardModel = new BoardModel(boardCode, nickname, destination, gender, minage, maxage, purpose, date);
                    dataList.add(boardModel);
                }
                return dataList;
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            Log.i("database","111111111");
            e.printStackTrace();
        }

        return dataList;
    }

    public String getTitle() {
        return "보드리스트";
    }

    public void removeAllItems(){
        dataList.clear();
    }
    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        init();
        swipeRefreshLayout.setRefreshing(false);

        //swipeRefreshLayout.computeScroll();
        recyclerView.scrollToPosition(300 + 350*refreshCount);
    }

}
