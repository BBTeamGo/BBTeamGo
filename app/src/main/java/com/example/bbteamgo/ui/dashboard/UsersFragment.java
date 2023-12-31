package com.example.bbteamgo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import team.everywhere.chatapp2020.Chat;
import team.everywhere.chatapp2020.MyAdapter;
import team.everywhere.chatapp2020.R;
import team.everywhere.chatapp2020.User;
import team.everywhere.chatapp2020.UserAdapter;


public class UsersFragment extends Fragment {
    private static final String TAG = "UsersFragment";
    private DashboardViewModel dashboardViewModel;
    FirebaseDatabase database;

    private RecyclerView recyclerView;
    UserAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<User> userArrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_users, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        SharedPreferences sharedPref = getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String stEmail = sharedPref.getString("email","");

        userArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new UserAdapter(userArrayList, stEmail);
        recyclerView.setAdapter(mAdapter);

        DatabaseReference ref = database.getReference("users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+dataSnapshot.getValue().toString());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    User user = dataSnapshot1.getValue(User.class);
                    Log.d(TAG, "user: "+user.getEmail());
                    userArrayList.add(user);
                }
                mAdapter.notifyDataSetChanged();
            };


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }
}
