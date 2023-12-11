package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ManagerSelectBoothActivity extends AppCompatActivity implements BoothAdapter.OnBoothClickListener {
    private final static String TAG = "BoothSelect";

    private FirebaseFirestore database;

    List<BoothItem> booths = new ArrayList<>();
    String userEmail = null;
    String userid = null;
    String userMembership = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select_booth);

        Intent intent = getIntent();
        userid = intent.getStringExtra("USER_ID");
        userEmail = intent.getStringExtra("USER_EMAIL");

        database = FirebaseFirestore.getInstance();
        database.collection("User")
                .document(userid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                userMembership = data.get("membership").toString();

                                Log.d(TAG, "User Membership:" + userMembership);
                                makeBoothArray();
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }

                    }
                });
    }

    private void makeBoothArray() {
        database.collection("University")
                .document(userMembership)
                .collection("Booth")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = document.getData();

                                String pictureURL = Objects.requireNonNull(data.get("picture_url")).toString();
                                String title = Objects.requireNonNull(data.get("name")).toString();
                                String explainText = Objects.requireNonNull(data.get("explain_text")).toString();

                                booths.add(new BoothItem(pictureURL, title, explainText));
                            }

                            Log.d(TAG, "Get data Success");
                            makeRecyclerView();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void makeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BoothAdapter(getApplicationContext(), booths, this));

        Log.d(TAG, "RecyclerView End");
    }

    @Override
    public void onBoothClick(int position) {
        // 부스의 비밀번호 가져오기
        String[] boothPassword = new String[1];

        database.collection("University")
                .document(userMembership)
                .collection("Booth")
                .document(booths.get(position).getTitle())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                boothPassword[0] = Objects.requireNonNull(data.get("password")).toString();

                                // 비밀번호 입력 다이어로그 띄우기
                                ManagerBoothSelectEnterPasswordFragment passwordFragment = new ManagerBoothSelectEnterPasswordFragment();

                                // Bundle에 정보를 넣어서 Fragment에 설정합니다.
                                Bundle args = new Bundle();
                                args.putString("BOOTH_NAME", booths.get(position).getTitle());
                                args.putString("UNIVERSITY_NAME", userMembership);
                                args.putString("BOOTH_PASSWORD", boothPassword[0]);
                                args.putString("USER_EMAIL", userEmail);
                                args.putString("USER_ID", userid);

                                passwordFragment.setArguments(args);

                                passwordFragment.show(getSupportFragmentManager(), "PasswordDialogFragment");
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

}