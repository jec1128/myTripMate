/* 내가 수정할 부분 */
/* 보드 리스트를 보여주는 부분 */

package com.example.tripmate.Board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.tripmate.Board.Matching.MatchingConditionActivity;
import com.example.tripmate.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class fragmentBoard extends Fragment implements View.OnClickListener {
    private static fragmentBoard instance;
    private View boardFragment;
    private FloatingActionButton write;
    private FloatingActionButton matching;
    private static String nickname;
    private static RecyclerView recyclerView;
    private static BoardListAdapter adapter;
    private static ArrayList<BoardModel> dataList;

    //버튼
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;

    @SuppressLint("ValidFragment")
    private fragmentBoard() { }

    public static fragmentBoard getInstance() {
        if (instance == null) {
            instance = new fragmentBoard();
            return instance;
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boardFragment = inflater.inflate(R.layout.fragment_board, container, false);
        recyclerView = boardFragment.findViewById(R.id.boardfragment_recyclerview);

        Log.i("몇번불릴까","111111");
        init();
        //이전 화면에서 닉네임 얻어오기
        Bundle extra = this.getArguments();

        if (extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
            System.out.println("fragment board : " + nickname);
        }

        //버튼 애니메이션을 위한 부분
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        fab = (FloatingActionButton) boardFragment.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) boardFragment.findViewById(R.id.boardfragment_button_write);
        fab2 = (FloatingActionButton) boardFragment.findViewById(R.id.boardfragment_button_matching);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        //write = boardFragment.findViewById(R.id.boardfragment_button_write);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }
        });

       // matching = boardFragment.findViewById(R.id.boardfragment_button_matching);
        fab2.setOnClickListener(new View.OnClickListener() {
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

    //버튼 애니메이션을 위한 부분
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                break;
            case R.id.boardfragment_button_write:
                anim();
                break;
            case R.id.boardfragment_button_matching:
                anim();
                break;
        }
    }

    public void anim() {
        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    //리사이클러뷰 초기화
    public void init() {
        // Log.i("1111","dddddddddd");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        // Log.i("22222","dddddddddd");
        adapter = new BoardListAdapter(jsonParserForGetData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //제이슨 파싱해서 데이터 리스트 얻어오는 부분
    public ArrayList<BoardModel> jsonParserForGetData() {

        HttpBoardList httpBoardDataActivity = new HttpBoardList();
        HttpBoardList.sendTask send = httpBoardDataActivity.new sendTask();

        String result = null;
        dataList = new ArrayList<>();

        try {
            result = send.execute("1").get(); //page번호 전송
            JSONArray jarray = null;
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

                    //파서해서 데이터 입력
                    Log.i("aaaaaa",nickname);
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

}
