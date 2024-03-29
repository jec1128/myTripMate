/*

package com.example.tripmate.Chat;

import android.app.ActivityOptions;
import android.os.Bundle;

import com.example.tripmate.R;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tripmate.User.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class fragmentFriend extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.friendfragment_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        recyclerView.setAdapter(new PeopleFragmentRecyclerViewAdapter());


        return view;
    }

    public class PeopleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<UserModel> userModels = null;
        String myUid;

        public PeopleFragmentRecyclerViewAdapter() {
            userModels = new ArrayList<>();

            if (FirebaseAuth.getInstance().getCurrentUser().getUid() != null) {
                myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase.getInstance().getReference().child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        userModels.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserModel userModel = snapshot.getValue(UserModel.class);

                            if ((userModel.getUid()).equals(myUid)) {
                                continue;
                            }
                            userModels.add(userModel);
                        }
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);


            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


            Glide.with
                    (holder.itemView.getContext())
                    .load(userModels.get(position).getProfileImageUrl())
                    .apply(new RequestOptions().circleCrop())
                    .into(((CustomViewHolder) holder).imageView);

            ((CustomViewHolder) holder).textView.setText(userModels.get(position).getUserName());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                //이 부분이 채팅화면으로 넘어가는 곳
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MessageActivity.class);
                    intent.putExtra("destinationUid", userModels.get(position).getUid());
                    intent.putExtra("destinationNickname",userModels.get(position).getUserName());
                    ActivityOptions activityOptions = null;
                    activityOptions = ActivityOptions.makeCustomAnimation(view.getContext(), R.anim.fromright, R.anim.toleft);
                    startActivity(intent, activityOptions.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return userModels.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;

            public CustomViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.frienditem_imageview);
                textView = (TextView) view.findViewById(R.id.frienditem_textview);
            }
        }
    }

}
*/
