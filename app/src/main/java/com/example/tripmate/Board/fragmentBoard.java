package com.example.tripmate.Board;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripmate.R;


public class fragmentBoard extends Fragment {

    private Button write;
    private String nickname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        //        // Inflate the layout for this fragment
        Bundle extra = this.getArguments();
        if(extra != null) {
            extra = getArguments();
            nickname = extra.getString("nickname");
        }
        System.out.println("fragmentBoard " + nickname);
        write = view.findViewById(R.id.board_button_write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                intent.putExtra("nickname",nickname);
                startActivity(intent);

            }
        });
        return view;
    }


}
