package com.example.tripmate.Board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tripmate.R;


public class fragmentBoard extends Fragment {
    private static fragmentBoard instance;
    private FloatingActionButton write;
    private FloatingActionButton matching;
    private static String nickname;
    private BoardListAdapter boardListadapter;
    private RecyclerView recyclerView;
    private View view;


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
        view = inflater.inflate(R.layout.fragment_board, container, false);
        // Inflate the layout for this fragment
        Bundle extra = this.getArguments();

        if (extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
            System.out.println("fragment board : " + nickname);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.boardfragment_recyclerview);


        //boardListadapter = BoardListAdapter.getInstance();
        boardListadapter = new BoardListAdapter();
        boardListadapter.httpwork();
        recyclerView.setAdapter(boardListadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));


        write = view.findViewById(R.id.boardfragment_button_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);

            }
        });

        matching = view.findViewById(R.id.boardfragment_button_matching);
        matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }

    public String getNickname() {
        return nickname;
    }

    public void doAdapter(){
        boardListadapter = new BoardListAdapter();
        boardListadapter.httpwork();
        recyclerView.setAdapter(boardListadapter);
    }

}
