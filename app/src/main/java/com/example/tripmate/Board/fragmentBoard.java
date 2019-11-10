package com.example.tripmate.Board;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.Chat.fragmentChatroom;
import com.example.tripmate.R;
import com.example.tripmate.User.HttpUserRegister;
import com.example.tripmate.fragmentActivity4;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Response;


public class fragmentBoard extends Fragment {

    private FloatingActionButton write;
    private static String nickname;
    private BoardListAdapter adapter;
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_board, container, false);

        // Inflate the layout for this fragment
        Bundle extra = this.getArguments();

        if (extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
            System.out.println("fragment board : " + nickname);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.boardfragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        adapter = BoardListAdapter.getInstance();
        adapter.httpwork();
        recyclerView.setAdapter(adapter);

        write = view.findViewById(R.id.boardfragment_button_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);

            }
        });

        return view;
    }

    public String getNickname(){
        return nickname;
    }

}
