package com.example.bbteamgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    String userid = null;
    String userMembership = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_select_booth);

        database = FirebaseFirestore.getInstance();

        getUserId();
    }

    private void getUserId() {
        Intent intent = getIntent();
        String userProfile = intent.getStringExtra("USER_PROFILE");

        if (userProfile != null) {
            String[] parts = userProfile.split("\n"); // 문자열을 분리하여 배열로 변환
            String uidPart = parts[1]; // 첫 번째 부분에는 email 정보가 있습니다.
            userid = uidPart.split(": ")[1]; // emailPart를 ": "으로 분리하여 email 정보만 추출
        }

        Log.d(TAG, "UserId:" + userid);
        getUserMembership();
        return;
    }

    private void getUserMembership() {
        database.collection("User").document(userid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                                Log.d(TAG, "Push Booth Success");
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

        Log.d(TAG, "1");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d(TAG, "2");
        recyclerView.setAdapter(new BoothAdapter(getApplicationContext(), booths, this));

        Log.d(TAG, "RecyclerView End");
    }

    @Override
    public void onBoothClick(int position) {
        Toast.makeText(this, "클릭한 아이템: " + booths.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        // 비밀번호 입력 다이어로그 띄우기
    }

}