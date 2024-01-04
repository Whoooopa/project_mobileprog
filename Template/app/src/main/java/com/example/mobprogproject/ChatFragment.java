package com.example.mobprogproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobprogproject.adapter.RecentChatRecyclerAdapter;
import com.example.mobprogproject.adapter.SearchUserRecyclerAdapter;
import com.example.mobprogproject.model.ChatroomModel;
import com.example.mobprogproject.model.UserModel;
import com.example.mobprogproject.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class ChatFragment extends Fragment {

    RecyclerView recyclerView;
    RecentChatRecyclerAdapter recentChatRecyclerAdapter;
    public ChatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.recyler_view);
        setUpRV();

        return view;
    }

    void setUpRV() {

        Query query = FirebaseUtil.allChatroomCollectionReference()
                .whereArrayContains("userIds", FirebaseUtil.currentUserId())
                .orderBy("lastMessageTimestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ChatroomModel> options = new FirestoreRecyclerOptions.Builder<ChatroomModel>()
                .setQuery(query, ChatroomModel.class).build();

        recentChatRecyclerAdapter = new RecentChatRecyclerAdapter(options, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recentChatRecyclerAdapter);
        recentChatRecyclerAdapter.startListening();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (recentChatRecyclerAdapter != null){
            recentChatRecyclerAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recentChatRecyclerAdapter != null){
            recentChatRecyclerAdapter.stopListening();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (recentChatRecyclerAdapter != null){
            recentChatRecyclerAdapter.notifyDataSetChanged();
        }
    }
}